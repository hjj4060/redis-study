package inaction.section4

import java.io.File

/**
 * object는 싱글턴 객체를 쉽게 만들 수 있는 방법입니다. object를 사용하면 클래스의 인스턴스를 하나만 생성하고, 그 인스턴스를 전역적으로 접근할 수 있습니다.
 */
class Person(val name: String, val pay: Int)

// 싱글턴 객체로 직원들 데이터와 연봉계산
object Payroll {
    val allEmployees = arrayListOf<Person>()

    fun calculateSalary(): Int {
        return allEmployees.sumOf {
            it.pay
        }
    }
}

// Object는 데이터저장없이 연산작업만 할 경우에 유용
object CaseInsensitiveComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

// 클래스 내부에 Object
class Character(val name: String) {
    object GameRule {
        val maxLevel = 99
    }
}

fun main () {
    val person1 = Person("Alice", 5000)
    val person2 = Person("Bob", 6000)
    Payroll.allEmployees.addAll(listOf(person1, person2))
    println(Payroll.calculateSalary())

    val files = listOf(File("d.txt"), File("B.txt"), File("a.txt"))
    val sortedFiles = files.sortedWith(CaseInsensitiveComparator)
    println(sortedFiles)

    val game1 = Character("Alice")
    val game2 = Character("babarian")
    println(Character.GameRule.maxLevel)
}