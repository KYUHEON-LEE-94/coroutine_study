import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    :
 * fileName       : runBlockingMain
 * author         : LEE KYUHEON
 * date           : 2024-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-14        LEE KYUHEON       최초 생성
 */

//fun main(args: Array<String>) {
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    runBlocking {
//        delay(2000L)
//    }
//}

//fun main(args: Array<String>) = runBlocking {
//    GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    delay(2000L)
//}

//fun main(args: Array<String>) = runBlocking {
//    val job = GlobalScope.launch {
//        delay(1000L)
//        println("World!")
//    }
//    println("Hello,")
//    job.join()
//}

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}