package coroutine_lecture.flow

// 1초마다 1개씩 데이터를 만들어 내는 함수
fun fetchList(): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 1..10) {
        Thread.sleep(1000) // DB/API 대기시간 1초
        list.add(i)
    }
    return list // 3초 뒤에 리스트 완성!
}

fun main() {
    println("--- List + for문 시작 ---")
    val data = fetchList() // ⚠️ 3초 동안 여기서 완전히 멈춰있음 (Blocking)
    for (value in data) {
        println(value) // 3초 뒤에 1, 2, 3이 한번에 찍힘
    }
}