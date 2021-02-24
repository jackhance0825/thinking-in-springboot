#### 1. actuator
```yaml
management:
   endpoints:
      enabled-by-default: true  #默认开启所有监控端点  true
      web:
         exposure:
            include: '*' # 以web方式暴露所有端点
```

测试: 
```text
http://localhost:8080/actuator/beans
http://localhost:8080/actuator/configprops
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/metrics/jvm.gc.pause
http://localhost:8080/actuator/endpointName/detailPath
```

最常用的Endpoint: 
- Health：监控状况
- Metrics：运行时指标
- Loggers：日志记录


##### 1.1. health Endpoint
/actuator/health

健康检查端点，我们一般用于在云平台，平台会定时的检查应用的健康状况，我们就需要Health Endpoint可以为平台返回当前应用的一系列组件健康状况的集合。
重要的几点：
- health endpoint返回的结果，应该是一系列健康检查后的一个汇总报告
- 很多的健康检查默认已经自动配置好了，比如：数据库、redis等
- 可以很容易的添加自定义的健康检查机制

#### 1.2. metrics Endpoint
/actuator/metrics

提供详细的、层级的、空间指标信息，这些信息可以被pull（主动推送）或者push（被动获取）方式得到；
- 通过Metrics对接多种监控系统
- 简化核心Metrics开发
- 添加自定义Metrics或者扩展已有Metrics

```json
{
   names: [
      "http.server.requests",
      "jvm.buffer.count",
      "jvm.buffer.memory.used",
      "jvm.buffer.total.capacity",
      "jvm.classes.loaded",
      "jvm.classes.unloaded",
      "jvm.gc.live.data.size",
      "jvm.gc.max.data.size",
      "jvm.gc.memory.allocated",
      "jvm.gc.memory.promoted",
      "jvm.gc.pause",
      "jvm.memory.committed",
      "jvm.memory.max",
      "jvm.memory.used",
      "jvm.threads.daemon",
      "jvm.threads.live",
      "jvm.threads.peak",
      "jvm.threads.states",
      "logback.events",
      "process.cpu.usage",
      "process.start.time",
      "process.uptime",
      "system.cpu.count",
      "system.cpu.usage",
      "tomcat.sessions.active.current",
      "tomcat.sessions.active.max",
      "tomcat.sessions.alive.max",
      "tomcat.sessions.created",
      "tomcat.sessions.expired",
      "tomcat.sessions.rejected"
   ]
}
```

查看单个metric ： /actuator/metrics/jvm.memory.max



#### 2. 定制化 Endpoint
##### 2.1. 定制化 Health Endpoint
继承 AbstractHealthIndicator 可实现健康情况扩展
其他内置健康检测，可查看 AbstractHealthIndicator 实现类


##### 2.2. 定制化 Info Endpoint
方式1：
application.yaml
```yaml
info:
  appName: springboot-08-manage
  appVersion: 1.0.0
  mavenProjectName: @project.artifactId@ # 取 pom 文件 artifactId 的值
  mavenProjectVersion: @project.version@ # 取 pom 文件 version 的值
```

方式2：
实现 InfoContributor 的组件

俩种方式的info都会汇总

##### 2.3. 定制化 Endpoint
```java
@Component
@Endpoint(id = "container")
public class DockerEndpoint {
    @ReadOperation
    public Map getDockerInfo(){
        return Collections.singletonMap("info","docker started...");
    }

    @WriteOperation
    private void restartDocker(){
        System.out.println("docker restarted....");
    }
}
```
/actuator/container

@ReadOperation GET
@WriteOperation POST
@DeleteOperation DELETE

场景：开发ReadinessEndpoint来管理程序是否就绪，或者LivenessEndpoint来管理程序是否存活；

#### 3. 可视化 Endpoint
https://github.com/codecentric/spring-boot-admin

client:
```yaml
# 可视化配置
spring:
  boot:
    admin:
      client:
        url: http://localhost:8888
        instance:
          prefer-ip: true  #使用ip注册进来
  application:
    name: springboot-08-manage
```
```xml
<project>
    <!-- 可视化 -->
    <dependency>
        <groupId>de.codecentric</groupId>
        <artifactId>spring-boot-admin-starter-client</artifactId>
        <version>2.3.1</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</project>
```

server:
springboot-09-actuatorserver
登录： http://localhost:8888/

#### 4. Profile
1. 指定适配环境
@Profile("prod") 

2. application-profile功能
默认配置文件  application.yaml；任何时候都会加载
指定环境配置文件  application-{env}.yaml
激活指定环境
配置文件激活
命令行激活：java -jar xxx.jar --spring.profiles.active=prod  --person.name=haha
修改配置文件的任意值，命令行优先
默认配置与环境配置同时生效
同名配置项，profile配置优先

3. 分组
```text
spring.profiles.group.production[0]=proddb
spring.profiles.group.production[1]=prodmq

使用：--spring.profiles.active=production  激活
```

4. 外部配置源
常用：Java属性文件、YAML文件、环境变量、命令行参数；

4.1. 配置文件查找位置
(1) classpath 根路径
(2) classpath 根路径下config目录
(3) jar包当前目录
(4) jar包当前目录的config目录
(5) /config子目录的直接子目录

4.2. 配置文件加载顺序：
1. 　当前jar包内部的application.properties和application.yml
2. 　当前jar包内部的application-{profile}.properties 和 application-{profile}.yml
3. 　引用的外部jar包的application.properties和application.yml
4. 　引用的外部jar包的application-{profile}.properties 和 application-{profile}.yml 
指定环境优先，外部优先，后面的可以覆盖前面的同名配置项