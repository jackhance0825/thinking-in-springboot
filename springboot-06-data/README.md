#### 1. myBatis整合

方式1：
Mapper.class 标注@Mapper

配置文件指定Mapper.xml位置 ： 
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

方式2：
配置类标注： @MapperScan("com.jackhance.springboot.mapper")

配置文件指定Mapper.xml位置 ：
```yaml
mybatis:
  mapper-locations: classpath:mapper/*.xml
```

#### 2. myBatis-plus整合
MyBatis-Plus（简称 MP）是一个 MyBatis 的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。
mybatis plus 官网 ： https://baomidou.com/

准备：
1. MybatisX 插件
2. 依赖
```xml
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.1</version>
</dependency>
```
3. 
BaseMapper<T>
IService<T>
ServiceImpl<M extends BaseMapper<T>, T>

#### 3. redis整合
```xml
<project>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <!-- 默认lettuce，切换为Jedis -->
    <dependency>
        <groupId>redis.clients</groupId>
        <artifactId>jedis</artifactId>
    </dependency>
</project>
```

```yaml
spring:
    redis:
      password: 123456
      host: 127.0.0.1
      port: 6379
      client-type: jedis # 默认 lettuce
      jedis:
        pool:
          max-active: 10
          min-idle: 5
```


