package part2

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

/**
 *packageName    : part2
 * fileName       : NonCancellable
 * author         : LEE KYUHEON
 * date           : 2024-09-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-18        LEE KYUHEON       최초 생성
 */
//fun main(args: Array<String>) = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            withContext(NonCancellable) {
//                delay(1000)
//                println("main : I'm running finally!")
//            }
//        }
//    }
//
//    delay(1300L)
//    println("main : I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main : Now I can quit.")
//}


fun main(args: Array<String>) = runBlocking {
    withTimeout(1300L) {
        launch {
            try {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                println("main : I'm running finally!")
            }
        }
    }
}