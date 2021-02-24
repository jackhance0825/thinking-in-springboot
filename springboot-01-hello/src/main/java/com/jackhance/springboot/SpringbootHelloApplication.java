package com.jackhance.springboot;

import com.jackhance.springboot.pojo.CarProperties;
import com.jackhance.springboot.pojo.Pet;
import com.jackhance.springboot.pojo.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 主程序类
 * <p>
 * SpringBootApplication：这是一个SpringBoot应用
 * <p>
 * SpringBoot先加载所有的自动配置类  xxxxxAutoConfiguration
 * 每个自动配置类按照条件进行生效，默认都会绑定配置文件指定的值。xxxxProperties里面拿。xxxProperties和配置文件进行了绑定
 * 生效的配置类就会给容器中装配很多组件
 * 只要容器中有这些组件，相当于这些功能就有了
 * 定制化配置
 * 用户直接自己@Bean替换底层的组件
 * 用户去看这个组件是获取的配置文件什么值就去修改。
 * <p>
 * xxxxxAutoConfiguration ---> 组件  ---> xxxxProperties里面拿值  ----> application.properties
 */
@SpringBootApplication
public class SpringbootHelloApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootHelloApplication.class, args);

        //        String[] beanNames = context.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }

        System.out.println("=================================");

        User user01 = context.getBean("user01", User.class);
        System.out.println(user01);
        Config config = context.getBean(Config.class);
        System.out.println(config.user01() == user01);

        System.out.println("=================================");

        ObjectProvider<User> userProvider = context.getBeanProvider(User.class);
        userProvider.stream().forEach(System.out::println);

        Pet pet01 = context.getBean("pet01", Pet.class);
        System.out.println(pet01);
        System.out.println("=================================");

        CarProperties carProperties = context.getBean(CarProperties.class);
        System.out.println(carProperties);
    }

}
