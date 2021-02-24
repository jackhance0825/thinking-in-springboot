#### 1. 静态资源路径
默认：/static or /public or /resources or /META-INF/resources
可指定静态资源路径：spring.web.resources.static-locations
注意：使默认资源路径失效，除非都配置上

#### 2. 静态资源访问路径
spring.mvc.static-path-pattern
默认：/**
可指定新的根路径，如：/res/**

#### 3. webjar
https://www.webjars.org/
/webjars/**

如： http://localhost:8080/webjars/jquery/3.5.1/jquery.js

#### 4. 静态资源访问流程
Controller是否能处理，若能，则处理并返回，若不能，则找静态资源

#### 5. 欢迎页
index.html 放在资源路径里就可访问
注意：若修改了spring.mvc.static-path-pattern，则无法正常访问（当前版本bug）

#### 6. Favicon
favicon.ioc 放在资源路径里即可
注意：若修改了spring.mvc.static-path-pattern，则无法正常访问（当前版本bug）

