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
            delay(1L) // delay() 함수는 suspend 함수이므로, 취소가 됐는지 확인하는 지점이 됩니다. delay() 함수가 없으면, whileJob이 취소된 후에도 whileJob의 코드가 계속 실행됩니다.
            //하지만 불필요하게 작업을 지연 시키는 경우가 있을 수 있기 때문에, delay() 함수를 추가하는 것은 좋은 방법이 아닙니다. 대신, isActive 속성을 사용하여 취소 여부를 확인하는 방법이 있습니다.
        }
    }

    delay(100L)
    whileJob.cancel()
}
