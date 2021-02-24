#### 1.异常处理

##### 1.1. error page
默认情况下，Spring Boot提供/error处理所有错误的映射
error/下的4xx，5xx页面会被自动解析

error/404.html   error/5xx.html；有精确的错误状态码页面就匹配精确，没有就找 4xx.html；如果都没有就触发白页

##### 1.2. 定制错误处理器
方式1：
@ControllerAdvice + @ExceptionHandler处理全局异常；
底层是 ExceptionHandlerExceptionResolver 支持的
```java
@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler({ArithmeticException.class, NullPointerException.class, UserTooManyException.class})  //处理异常
   public String handleArithException(Exception e) {
      log.error("GlobalExceptionHandler 异常是：{}", e);
      return "/login"; //视图地址
   }
}
```

方式2：
实现组件HandlerExceptionResolver
```java
@Component
@Slf4j
public class CustomerHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        log.error("CustomerHandlerExceptionResolver 异常是：{}", ex);
        try {
            response.sendError(511,"我喜欢的错误");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
}
```

##### 1.3. 定制异常
@ResponseStatus(value= HttpStatus.FORBIDDEN,reason = "用户数量太多")

#### 2. Web原生组件注册
方式1：
@ServletComponentScan
@WebFilter(urlPatterns={"/css/*","/images/*"})
@WebServlet(urlPatterns = "/my")
@WebListener

方式2：
@Bean
FilterRegistrationBean
ServletRegistrationBean
ServletListenerRegistrationBean

#### 3. 定制化内嵌web服务器
切换web服务器：
```xml
<project>
    <!-- 默认使用tomcat，下面切换成Jetty -->
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jetty</artifactId>
    </dependency>
</project>
```

定制化服务器配置：
implements WebServerFactoryCustomizer<ConfigurableJettyWebServerFactory>

#### 4. 定制化套路
场景starter - xxxxAutoConfiguration - 导入xxx组件 - 绑定xxxProperties -- 绑定配置文件项