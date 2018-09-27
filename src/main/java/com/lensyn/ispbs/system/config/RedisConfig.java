package com.lensyn.ispbs.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	
	@Bean
	@ConfigurationProperties
	public JedisPoolConfig getRedisConfig() {
		JedisPoolConfig config = new JedisPoolConfig();
		return config;
	}

	@SuppressWarnings("deprecation")
	@Bean
	@ConfigurationProperties
	public JedisConnectionFactory getConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(host);
		factory.setPort(port);
		factory.setUsePool(true);
		JedisPoolConfig config = getRedisConfig();
		config.setMaxIdle(10);
		config.setMaxTotal(100);
		config.setMaxWaitMillis(100000);
		factory.setPoolConfig(config);
		return factory;
	}

	@Bean
	public RedisTemplate<?, ?> getRedisTemplate() {
		JedisConnectionFactory factory = getConnectionFactory();
		RedisTemplate<?, ?> template = new StringRedisTemplate(factory);
		return template;
	}
}