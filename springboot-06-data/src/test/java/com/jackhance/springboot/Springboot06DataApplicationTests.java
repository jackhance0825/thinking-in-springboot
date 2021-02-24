package com.jackhance.springboot;

import com.jackhance.springboot.model.User;
import com.jackhance.springboot.service.UserPlusService;
import com.jackhance.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Springboot06DataApplicationTests {

	@Autowired
	JdbcTemplate template;

	@Test
	void contextLoads() {
		List<Map<String, Object>> users = template.queryForList("select * from user");
		System.out.println(users);
	}

	@Autowired
	DataSource dataSource;

	@Test
	void dataSource() {
		System.out.println(dataSource.getClass());
	}

	@Autowired
	UserService userService;

	@Test
	void userMapper() {
		User u = userService.selectUserById(1);
		System.out.println(u);
	}

	@Test
	void userMapper1() {
		User u = userService.selectUserById1(1);
		System.out.println(u);
	}

	@Autowired
	UserPlusService userPlusService;

	@Test
	void userPlusMapper() {
		User u = userPlusService.getById(1);
		System.out.println(u);
		System.out.println(userPlusService.count());
	}


	@Autowired
	StringRedisTemplate redisTemplate;

	@Resource
	RedisConnectionFactory redisConnectionFactory;

	@Test
	void testRedis() {
		System.out.println(redisTemplate.opsForValue().get("hello"));
		redisTemplate.opsForValue().set("hello","test1");
		System.out.println(redisTemplate.opsForValue().get("hello"));
		System.out.println(redisConnectionFactory.getClass());
	}



}
