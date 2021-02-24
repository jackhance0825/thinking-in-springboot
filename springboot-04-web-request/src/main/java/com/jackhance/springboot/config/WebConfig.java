package com.jackhance.springboot.config;


import com.jackhance.springboot.pojo.Person;
import com.jackhance.springboot.pojo.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StringUtils;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration(proxyBeanMethods = false)
public class WebConfig /*implements WebMvcConfigurer*/ {

    /**
     * form无法提交PUT、DELETE、PATCH，开启隐藏方法解决
     */
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        // 默认_method
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }

    /**
     * 新增自定义类型，实现方式有：
     * 1. WebMvcConfigurer # addFormatters 方法进行注册（官方推荐）
     * 2. 创建组件@Bean Converter
     */
    @Bean
    public Converter<String, Pet> petConverter() {
        return new Converter<String, Pet>() {
            @Override
            public Pet convert(String source) {
                // 啊猫,3
                if (!StringUtils.isEmpty(source)) {
                    Pet pet = new Pet();
                    String[] split = source.split(",");
                    pet.setName(split[0]);
                    pet.setAge(Integer.parseInt(split[1]));
                    return pet;
                }
                return null;
            }
        };
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {


            /**
             * 开启矩阵变量
             */
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                // 不移除；后面的内容。矩阵变量功能就可以生效 @MatrixVariable
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            // 处理自定义类型转换（官方推荐方式）
            /*@Override
            public void addFormatters(FormatterRegistry registry) {
                registry.addConverter(new Converter<String, Pet>() {

                    @Override
                    public Pet convert(String source) {
                        // 啊猫,3
                        if(!StringUtils.isEmpty(source)){
                            Pet pet = new Pet();
                            String[] split = source.split(",");
                            pet.setName(split[0]);
                            pet.setAge(Integer.parseInt(split[1]));
                            return pet;
                        }
                        return null;
                    }
                });
            }*/

            /**
             * 自定义内容协商策略
             */
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //Map<String, MediaType> mediaTypes
                Map<String, MediaType> mediaTypes = new HashMap<>();
                mediaTypes.put("json", MediaType.APPLICATION_JSON);
                mediaTypes.put("xml", MediaType.APPLICATION_XML);
                mediaTypes.put("x-app", MediaType.parseMediaType("application/x-app"));
                //指定支持解析哪些参数对应的哪些媒体类型
                ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
//                parameterStrategy.setParameterName("ff");

                configurer.strategies(Arrays.asList(parameterStrategy, new HeaderContentNegotiationStrategy()));
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new HttpMessageConverter<Person>() {
                    @Override
                    public boolean canRead(Class<?> clazz, MediaType mediaType) {
                        return false;
                    }

                    @Override
                    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
                        return clazz.isAssignableFrom(Person.class);
                    }

                    @Override
                    public List<MediaType> getSupportedMediaTypes() {
                        return MediaType.parseMediaTypes("application/x-app");
                    }

                    @Override
                    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
                        return null;
                    }

                    @Override
                    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
                        //自定义协议数据的写出
                        String data = person.getUserName() + " & " + person.getAge() + " & " + person.getBirth();
                        //写出去
                        outputMessage.getBody().write(data.getBytes(StandardCharsets.UTF_8));
                    }
                });
            }

        };
    }

///**
// * 开启矩阵变量
// */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//
//        UrlPathHelper urlPathHelper = new UrlPathHelper();
//        // 不移除；后面的内容。矩阵变量功能就可以生效
//        urlPathHelper.setRemoveSemicolonContent(false);
//        configurer.setUrlPathHelper(urlPathHelper);
//    }
}





