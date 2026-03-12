package com.example.demo.config

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
@EnableCaching
class RedisCacheConfig {

    @Bean
    fun boardCacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
        // 1. ObjectMapper 설정 (타입 정보 포함 활성화)
        val objectMapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerKotlinModule()
            .activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                    .allowIfBaseType(Any::class.java)
                    .build(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY
            )

        // 2. GenericJackson2JsonRedisSerializer 사용
        // 이 Serializer는 객체의 클래스 타입을 JSON에 포함시켜 저장합니다.
        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)
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