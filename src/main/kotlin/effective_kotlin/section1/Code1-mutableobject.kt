package effective_kotlin.section1

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

private class Person(var name: String) {
    override fun equals(other: Any?): Boolean {
        return (other as? Person)?.name == name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

fun main() {
    val person = Person("Alice")
    val set = mutableSetOf<Person>()

    set.add(person)
    println(set.contains(person))
    person.name = "Bob"
    println(set.contains(person))

    val won: Int = 1
    val cal = won.plus(2)
    println(cal)
    println()

    var list = listOf<Int>()
    var num = 0
    val lock = Any()
    val num2: AtomicInteger = AtomicInteger(0)
    for (i in 1..1000) {
        thread {
            synchronized(lock) {
                list = list + 1
                num += 1
                //num2.incrementAndGet()
            }
        }
    }
    println(list.size)
    println(num)
//    println(num2)
}