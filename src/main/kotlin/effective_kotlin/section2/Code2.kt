package effective_kotlin.section2

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread



fun main() {
    val map = ConcurrentHashMap<Int, String>()

    for (i in 1..1000) {
        thread {
            Thread.sleep(100)
            map.put(i, "E$i")
        }
        thread {
            Thread.sleep(100)
            println(map.toList().sumOf{it.first})
        }
    }
    Thread.sleep(200)
    println(map.size)
}