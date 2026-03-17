package coroutine_lecture.section1.code1

import kotlin.concurrent.thread

class ExampleThread: Thread() {
    override fun run() {
        println("[${Thread.currentThread().name}] 시작")
        Thread.sleep(2000L)
        println("[${Thread.currentThread().name}] 종료")
    }
}

fun main() {
    println("[${Thread.currentThread().name}] 시작")
    //ExampleThread().start()
    thread {
        println("[${Thread.currentThread().name}] 시작")
        Thread.sleep(2000L)
        println("[${Thread.currentThread().name}] 종료")
    }

    Thread.sleep(1000L)
    println("[${Thread.currentThread().name}] 종료")
}