//package com.example.demo
//
//import org.apache.lucene.analysis.ko.KoreanAnalyzer
//import org.apache.lucene.analysis.tokenattributes.CharTermAttribute
//import java.io.StringReader
//import org.junit.jupiter.api.Test
//import kotlin.jvm.java
//import org.apache.lucene.analysis.ko.KoreanAnalyzer
//
//class NoriAnalysisTest {
//
//    @Test
//    fun `한글_문장_인덱싱_테스트`() {
//        val text = "구글 드라이브에서 파일을 검색하고 다운로드합니다."
//
//        // 1. Nori 분석기 생성
//        // KoreanAnalyzer는 기본적으로 조사를 제거하고 명사 위주로 뽑아줍니다.
//        val analyzer = KoreanAnalyzer()
//
//        val tokens = mutableListOf<String>()
//
//        // 2. TokenStream 생성
//        val tokenStream = analyzer.tokenStream("myField", StringReader(text))
//        val charTermAttribute = tokenStream.addAttribute(CharTermAttribute::class.java)
//
//        try {
//            tokenStream.reset()
//            while (tokenStream.incrementToken()) {
//                // 분석된 단어 추출
//                val term = charTermAttribute.toString()
//                tokens.add(term)
//            }
//            tokenStream.end()
//        } finally {
//            tokenStream.close()
//        }
//
//        // 결과 출력
//        println("원문: $text")
//        println("분석 결과: $tokens")
//
//        // 예상 출력: [구글, 드라이브, 파일, 검색, 다운로드]
//    }
//}