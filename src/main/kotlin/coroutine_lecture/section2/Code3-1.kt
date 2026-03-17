package coroutine_lecture.section2

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking

// 단일 스레드 디스패처
@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
val singleThreadDispatcher: CoroutineDispatcher = newSingleThreadContext("SingleThread")

fun main() = runBlocking<Unit> {
    launch(singleThreadDispatcher) {
        println("[${Thread.currentThread().name}] launch 코루틴 실행")
    }
}