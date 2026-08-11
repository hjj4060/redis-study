package inaction.section4

// 동반객체 인터페이스
interface ItemFactory<T, P> {
    fun create(param: P): T
}

class Sword(val damage: Int) {
    companion object : ItemFactory<Sword, Int>{
        override fun create(param: Int): Sword = Sword(param)
    }
}

class Shield(val defence: Int) {
    companion object : ItemFactory<Shield, Int> {
        override fun create(param: Int): Shield = Shield(param)
    }
}

fun <T, P> spawnItem(factory: ItemFactory<T, P>, param: P): T {
    println("아이템 생성중")
    return factory.create(param)
}

fun main() {
    val mySword = spawnItem(Sword, 50)
    println("검 공격력: ${mySword.damage}")
    val myShield = spawnItem(Shield, 100)
    println("방어력: ${myShield.defence}")
}