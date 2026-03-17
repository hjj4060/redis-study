package coroutine_lecture.section5

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 취소처리
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val longJob: Job = launch(Dispatchers.Default) {
        repeat(10){
            delay(1000L)
            println("${getElapsedTime(startTime)}ms: lazyJob 시작")
        }
    }
    delay(2000L)
    longJob.cancel() // cancel()함수로 longJob이 취소됩니다.
}

private fun getElapsedTime(startTime: Long): String =
    "지난 시간: ${System.currentTimeMillis() - startTime}"
