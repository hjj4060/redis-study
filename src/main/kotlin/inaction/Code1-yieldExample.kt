package inaction

import kotlinx.coroutines.*
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
fun now() = ZonedDateTime.now().toLocalTime().truncatedTo (ChronoUnit.MILLIS)
fun log (msg:String) = println("${now()}: ${Thread.currentThread()}: $msg")

fun yieldExample() {
    runBlocking {
        launch {
            log("1")
            yield()
            log("3")
            yield()
            log("5")
        }
        println("after first launch")
        launch {
            log("2")
            throw Exception("test exception")
            delay(1000L)
            log("4")
            delay(2000L)
            log("6")
        }
        println("after second launch")
    }
}

fun main() {
    yieldExample()
}