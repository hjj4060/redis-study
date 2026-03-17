package coroutine_lecture.section2

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

// 멀티 스레드 디스패처
@OptIn(DelicateCoroutinesApi::class)
val multiThreadDispatcher: CoroutineDispatcher = newFixedThreadPoolContext(2, "MultiThread")

fun main() = runBlocking {
    repeat(10) {
        launch(multiThreadDispatcher) {
            println("[${Thread.currentThread().name}] launch 코루틴 실행")
        }
    }
}