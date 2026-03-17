package coroutine_lecture.section5

import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴 순차처리
fun main() = runBlocking<Unit> {
    // 1. 작업을 시키고 '접수 번호(Job)'를 받습니다.
    val convertImageJob1: Job = launch {
        println("[${Thread.currentThread().name}] 이미지 변환 작업 1 시작")
        kotlinx.coroutines.delay(1000L)
        println("[${Thread.currentThread().name}] 이미지 변환 작업 1 종료")
    }

    // 1. 작업을 시키고 '접수 번호(Job)'를 받습니다.
    val convertImageJob2: Job = launch {
        println("[${Thread.currentThread().name}] 이미지 변환 작업 2 시작")
        kotlinx.coroutines.delay(1000L)
        println("[${Thread.currentThread().name}] 이미지 변환 작업 2 종료")
    }

    // 2. joinAll은 Job들을 보고 "너네 둘 다 끝날 때까지 여기서 대기할게"라고 선언합니다.
    // 이게 없으면 이미지 변환이 끝나기도 전에 업로드 작업이 시작될 수 있습니다.
    joinAll(convertImageJob1, convertImageJob2)

    // 3. 위 두 Job이 모두 완료(Completed) 상태가 되어야만 이 줄로 내려옵니다.
    val uploadImageJob: Job = launch {
        println("[${Thread.currentThread().name}] 이미지 업로드 작업 시작")
        kotlinx.coroutines.delay(1000L)
        println("[${Thread.currentThread().name}] 이미지 업로드 작업 종료")
    }
}