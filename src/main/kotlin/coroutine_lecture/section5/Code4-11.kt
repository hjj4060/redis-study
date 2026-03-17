package coroutine_lecture.section5

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 취소처리 cancel() 함수로 longJob이 취소되지만, longJob의 코드가 계속 실행되는 예시입니다.
fun main() = runBlocking<Unit> {
    val longJob = launch(Dispatchers.Default) {
        repeat(10){
            println("${getElapsedTime(System.currentTimeMillis())}ms: longJob 시작")
        }
    }

//    longJob.cancel() // 취소 플래그만 켜지는 상태입니다. longJob이 취소된 후에도 longJob의 코드가 계속 실행됩니다.
    longJob.cancelAndJoin() // cancelAndJoin() 함수는 longJob이 취소된 후에 longJob의 코드가 더 이상 실행되지 않도록 합니다.
    executeAfterJobCancelled() // 이 함수가 실행된 후에도 longJob 코드가 실행됩니다.
}

private fun getElapsedTime(startTime: Long): String =
    "지난 시간: ${System.currentTimeMillis() - startTime}"

private fun executeAfterJobCancelled() {
    println("longJob이 취소된 후에 실행되는 함수입니다.")
}