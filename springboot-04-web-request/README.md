#### 1. rest
Rest风格支持（使用HTTP请求方式动词来表示对资源的操作）
以前：/getUser   获取用户     /deleteUser 删除用户    /editUser  修改用户       /saveUser 保存用户 
现在： /user    GET-获取用户    DELETE-删除用户     PUT-修改用户      POST-保存用户 

核心Filter；HiddenHttpMethodFilter
用法： 表单method=post，隐藏域 _method=put
SpringBoot中手动开启
```yaml
spring:
  mvc:
    hiddenmethod:
      filter:
        enabled: true
```

如果想自定义隐藏域属性：可以自定义HiddenHttpMethodFilter，然后调用setMethodParam设置想要的属性名
```java
import org.springframework.context.annotation.Configuration;

@Configuration
class Config {
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }
}
```

#### 2. rest请求原理(Filter拦截后，HttpMethodRequestWrapper重新包装)
表单提交会带上_method=PUT
请求过来被HiddenHttpMethodFilter拦截
请求是否正常，并且是POST
获取到_method的值。
兼容以下请求: PUT.DELETE.PATCH
原生request（post），包装模式requesWrapper重写了getMethod方法，返回的是传入的值。
过滤器链放行的时候用wrapper。以后的方法调用getMethod是调用requesWrapper的。

postman可以直接发送put、delete，无需包装

#### 3. 映射原理
DispatcherServlet初始化时，保存好所有的HandlerMapping
调用不同请求方法，触发不同的doXX方法，嵌套调用doService
doService根据request对象查找匹配的HandlerMapping
请求处理分发到HandlerMapping

SpringBoot自动配置欢迎页的 WelcomePageHandlerMapping 。访问 /能访问到index.html；
SpringBoot自动配置了默认 的 RequestMappingHandlerMapping

#### 4. 开启矩阵变量
默认关闭
开启时，可自定义WebMvcConfigurer # configurePathMatch

UrlPathHelper # setRemoveSemicolonContent(false);

#### 5. 新增自定义类型
实现方式有：
1. WebMvcConfigurer # addFormatters 方法进行注册（官方推荐）
2. 创建组件@Bean Converter

#### 6. 内容协商原理（需要 @ResponseBody）
0. 开启xml的支持
```xml
<!-- 开启jackson.xml支持 -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

1. 获取客户端支持的内容类型
contentNegotiationManager 内容协商管理器
默认使用HeaderContentNegotiationStrategy请求头策略获取，即Accept
2. 获取服务器支持的媒体类型（当前的HttpMessageConverter能处理的媒体类型）
2. 客户端支持类型与服务器支持类型最佳匹配
3. 使用HttpMessageConverter转换成目标类型

#### 7. 内容协商定制化
1. 内建参数控制媒体类型
   spring.mvc.contentnegotiation.favor-parameter
   使用ParameterContentNegotiationStrategy参数策略，format = xml or json or 自定义
2. 适配器接口定制化内容协商器
WebMvcConfigurer # configureContentNegotiation
注意：定制方法会覆盖，记得把HeaderContentNegotiationStrategy addLast，否则会造成其他请求无法处理
3. 定制化媒体类型
注册自定义HttpMessageConverter
WebMvcConfigurer # extendMessageConverters


