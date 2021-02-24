#### 1. Junit 5
Spring Boot 2.2.0 版本开始引入 JUnit 5 作为单元测试默认库
作为最新版本的JUnit框架，JUnit5与之前版本的Junit框架有很大的不同。由三个不同子项目的几个不同模块组成。

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Platform: Junit Platform是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入。

JUnit Jupiter: JUnit Jupiter提供了JUnit5的新的编程模型，是JUnit5新特性的核心。内部 包含了一个测试引擎，用于在Junit Platform上运行。

JUnit Vintage: 由于JUint已经发展多年，为了照顾老的项目，JUnit Vintage提供了兼容JUnit4.x,Junit3.x的测试引擎。

注意：
SpringBoot 2.4 以上版本移除了默认对 Vintage 的依赖。如果需要兼容junit4需要自行引入（不能使用junit4的功能 @Test）
JUnit 5’s Vintage Engine Removed from spring-boot-starter-test,如果需要继续兼容junit4需要自行引入vintage
```xml
<dependency>
    <groupId>org.junit.vintage</groupId>
    <artifactId>junit-vintage-engine</artifactId>
    <scope>test</scope>
    <exclusions>
        <exclusion>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```



测试starter依赖导入：
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```

测试类：
```java
@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Test
    void contextLoads() {
    }
}
```

SpringBoot整合Junit以后。
• 编写测试方法：@Test标注（注意需要使用junit5版本的注解）
• Junit类具有Spring的功能，@Autowired、比如 @Transactional 标注测试方法，测试完成后自动回滚

2. JUnit5常用注解
   JUnit5的注解与JUnit4的注解有所变化
   https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations
   
- @Test :表示方法是测试方法。但是与JUnit4的@Test不同，他的职责非常单一不能声明任何属性，拓展的测试将会由Jupiter提供额外测试
- @ParameterizedTest :表示方法是参数化测试，下方会有详细介绍
- @RepeatedTest :表示方法可重复执行，下方会有详细介绍
- @DisplayName :为测试类或者测试方法设置展示名称
- @BeforeEach :表示在每个单元测试之前执行
- @AfterEach :表示在每个单元测试之后执行
- @BeforeAll :表示在所有单元测试之前执行
- @AfterAll :表示在所有单元测试之后执行
- @Tag :表示单元测试类别，类似于JUnit4中的@Categories
- @Disabled :表示测试类或测试方法不执行，类似于JUnit4中的@Ignore
- @Timeout :表示测试方法运行如果超过了指定时间将会返回错误
- @ExtendWith :为测试类或测试方法提供扩展类引用

3. 断言（assertions）
断言（assertions）是测试方法中的核心部分，用来对测试需要满足的条件进行验证。这些断言方法都是 org.junit.jupiter.api.Assertions 的静态方法。JUnit 5 内置的断言可以分成如下几个类别：
检查业务逻辑返回的数据是否合理。
所有的测试运行结束以后，会有一个详细的测试报告；

- assertEquals	判断两个对象或两个原始类型是否相等
- assertNotEquals	判断两个对象或两个原始类型是否不相等
- assertSame	判断两个对象引用是否指向同一个对象
- assertNotSame	判断两个对象引用是否指向不同的对象
- assertTrue	判断给定的布尔值是否为 true
- assertFalse	判断给定的布尔值是否为 false
- assertNull	判断给定的对象引用是否为 null
- assertNotNull	判断给定的对象引用是否不为 null

4. 参数化测试
   参数化测试是JUnit5很重要的一个新特性，它使得用不同的参数多次运行测试成为了可能，也为我们的单元测试带来许多便利。
   利用@ValueSource等注解，指定入参，我们将可以使用不同的参数进行多次单元测试，而不需要每新增一个参数就新增一个单元测试，省去了很多冗余代码。
   
- @ValueSource: 为参数化测试指定入参来源，支持八大基础类以及String类型,Class类型 
- @NullSource: 表示为参数化测试提供一个null的入参
- @EnumSource: 表示为参数化测试提供一个枚举入参
- @CsvFileSource：表示读取指定CSV文件内容作为参数化测试入参 
- @MethodSource：表示读取指定方法的返回值作为参数化测试入参(注意方法返回需要是一个流)

