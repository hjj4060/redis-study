package com.example.demo.config

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.jasypt.encryption.StringEncryptor
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableEncryptableProperties
class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    fun stringEncryptor(): StringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        val config = SimpleStringPBEConfig().apply {
            password = System.getenv("JASYPT_PASSWORD") ?: System.getProperty("jasypt.encryptor.password")
            algorithm = "PBEWithMD5AndDES"
            setKeyObtentionIterations("1000")
            setPoolSize("1")
            setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
            stringOutputType = "base64"
        }
        encryptor.setConfig(config)
        return encryptor
    }
}
