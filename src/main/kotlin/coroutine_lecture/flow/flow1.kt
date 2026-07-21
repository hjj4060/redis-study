package coroutine_lecture.flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

// 1초마다 1개씩 데이터를 흘려보내는 Flow
fun fetchFlow(): Flow<Int> = flow {
    for (i in 1..10) {
        delay(1000) // 1초 대기 (스레드를 막지 않는 suspend)
        emit(i)     // 1초 지나자마자 즉시 방출!
    }
}

fun main() = runBlocking {
    println("--- Flow 시작 ---")
    fetchFlow().collect { value ->
        // 💡 1초 뒤에 1 출력 -> 또 1초 뒤에 2 출력 -> 또 1초 뒤에 3 출력!
        println(value)
    }
}