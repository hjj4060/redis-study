package coroutine_lecture.section5

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

// 코루틴 취소처리 cancel() 함수로 longJob이 취소되지만, longJob의 코드가 계속 실행되는 예시입니다.
fun main() = runBlocking<Unit> {
    val whileJob: Job = launch(Dispatchers.Default) {
        while (true) {
            println("작업중")
            yield() // yield() 함수는 suspend 함수이므로, 취소가 됐는지 확인하는 지점이 됩니다. delay()보다는 효율적이지만 일시정지후 재개하는 불필요한 작업이 필요하다.
        }
    }

    delay(100L)
    whileJob.cancel()
}
