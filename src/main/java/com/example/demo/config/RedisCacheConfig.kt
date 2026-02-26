package com.example.demo.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class RedisCacheConfig {

    @Bean
    fun boardCacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
        // 1. LocalDateTime을 지원하는 ObjectMapper 설정
        val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule()) // Java 8 날짜/시간 지원
            .registerKotlinModule() // 코틀린 클래스 지원

        // 2. 설정된 ObjectMapper를 사용하는 Serializer 생성
        val serializer = Jackson2JsonRedisSerializer(objectMapper, Any::class.java)

        val redisCacheConfiguration = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer())
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer) // 수정된 serializer 적용
            )
            .entryTtl(Duration.ofMinutes(1L))

        return RedisCacheManager
            .builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build()
    }
}