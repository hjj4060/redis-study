package coroutine_lecture.section2

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 이름 지정하기
fun main() = runBlocking<Unit>(CoroutineName("runBlockingCoroutine")) {
    println("[${Thread.currentThread().name}] runBlocking 코루틴 실행")

    launch(CoroutineName("launchCoroutine")) {
        println("[${Thread.currentThread().name}] launch 코루틴 실행")
    }
}