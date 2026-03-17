package coroutine_lecture.section5

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 지연처리
fun main() = runBlocking<Unit> {
    val startTime = System.currentTimeMillis()
    val lazyJob: Job = launch(start  = CoroutineStart.LAZY) {
        println("${getElapsedTime(startTime)}ms: lazyJob 시작")
    }
    delay(1000L)
    lazyJob.start() // start()함수로 lazyJob이 시작됩니다., join() 호출해도 lazyJob이 시작됩니다.
}

private fun getElapsedTime(startTime: Long): String =
    "지난 시간: ${System.currentTimeMillis() - startTime}"
