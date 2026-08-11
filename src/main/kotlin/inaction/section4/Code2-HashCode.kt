package inaction.section4

// equals() 오버라이딩 할때, hashCode()도 반드시 오버라이딩
class Client(val name: String, val portalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (other !is Client) return false
        return other.name == this.name && other.portalCode == this.portalCode
    }

    override fun hashCode(): Int = name.hashCode() * 31 + portalCode // 31 곱하는 이유는 해시 충돌을 줄이기 위해서입니다. 31은 홀수이면서 소수이기 때문에 곱셈 연산에서 좋은 분포를 만들어줍니다.
}

fun main() {
    val client1 = Client("오현석", 4122)
    val client2 = Client("오현석", 4122)

    println(client1 == client2)

    val processed = hashSetOf(client1)
    println(processed.contains(client2))
}