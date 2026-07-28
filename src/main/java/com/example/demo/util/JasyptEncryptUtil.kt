package com.example.demo.util

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig

/**
 * OpenAI API 키 암호화 유틸리티
 *
 * 사용법:
 *   1. JASYPT_PASSWORD 환경변수에 마스터 비밀번호 설정
 *   2. main() 실행 → 출력된 ENC(...) 값을 application.yaml에 붙여넣기
 */
fun main() {
    val masterPassword = System.getenv("JASYPT_PASSWORD")
        ?: error("환경변수 JASYPT_PASSWORD를 먼저 설정하세요.")

    val plainText = System.getenv("OPENAI_API_KEY_PLAIN")
        ?: error("환경변수 OPENAI_API_KEY_PLAIN에 암호화할 키를 설정하세요.")

    val encryptor = PooledPBEStringEncryptor().apply {
        setConfig(SimpleStringPBEConfig().apply {
            password = masterPassword
            algorithm = "PBEWithMD5AndDES"
            setKeyObtentionIterations("1000")
            setPoolSize("1")
            setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator")
            stringOutputType = "base64"
        })
    }

    val encrypted = encryptor.encrypt(plainText)
    println("암호화된 값 (application.yaml에 사용):")
    println("spring.ai.openai.api-key: ENC($encrypted)")
}
