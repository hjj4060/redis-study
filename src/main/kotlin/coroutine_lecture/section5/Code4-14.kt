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
    val whileJob: Job = launch(Dispatchers.Default) {
        while (true) {
            println("작업중")
        }
    }

    delay(100L)
    whileJob.cancel() //cancel()로 무한루프가 돌고 있는 whileJob이 취소되지 않습니다.
}
