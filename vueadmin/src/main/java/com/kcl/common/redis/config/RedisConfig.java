package com.kcl.common.redis.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 项目名： vueadmin
 * 包名:    com.kcl.common.redis.config
 * 文件名   RedisConfig
 * 创建者
 * 创建时间: 2021/6/4 2:00 PM
 * 描述  ${TODO}
 */

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate mRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;

    }
}

