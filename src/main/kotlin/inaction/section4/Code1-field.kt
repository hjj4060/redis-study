package inaction.section4

// 코틀린 인액션 168page
class UserBad {
    var address: String = "Seoul"
        set(value) {
            println("주소를 변경합니다.")
            // field 변수를 사용해서 실제 프로퍼티에 접근해야 한다, address = value를 사용하면 setter가 다시 호출되어 StackOverflowError 발생
            field = value
        }

    var age: Int = 20
        set(value) {
            println("나이를 변경합니다.")
            field = value
        }
}

fun main() {
    val user = UserBad()
    user.address = "Busan" // 주소를 변경합니다. -> StackOverflowError 발생
    user.age = 25 // 나이를 변경합니다.
    println(user.address)
    println(user.age)
}