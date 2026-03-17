package coroutine_lecture.section5

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

// 코루틴 취소처리
fun main() = runBlocking<Unit> {
    val whileJob: Job = launch(Dispatchers.Default) {
        while (this.isActive) { // isActive 속성을 사용하여 취소 여부를 확인하는 방법입니다. whileJob이 취소되면, isActive가 false가 되어 while 루프가 종료됩니다.
            println("작업중")
        }
    }

    delay(100L)
    whileJob.cancel() // 취소요청하는 순간 isActive가 false가 되어 while 루프가 종료됩니다.
}