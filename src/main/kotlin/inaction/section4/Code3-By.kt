package inaction.section4

interface Worker {
    fun work()
    fun eat()
}

class RealWorker : Worker {
    override fun work() {
        println("RealWorker is working")
    }

    override fun eat() {
        println("RealWorker is eating")
    }
}

// by 키워드를 사용하여 Worker 인터페이스를 위임합니다. Manager 클래스는 Worker 인터페이스의 구현을 RealWorker 인스턴스에 위임하고, work() 메서드를 오버라이드하여 추가적인 동작을 수행합니다.
class Manager(private val realWorker: Worker) : Worker by realWorker {
    override fun work() {
        println("Manager is managing")
        realWorker.work()
    }

    fun fire() {
        println("Manager says goodbye")
    }
}

fun main() {
    val realWorker = RealWorker()
    val manager = Manager(realWorker)

    manager.work()
    manager.eat()
    manager.fire()
}