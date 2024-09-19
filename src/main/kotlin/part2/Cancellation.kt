package part2

import kotlinx.coroutines.*
import java.io.Closeable

/**
 *packageName    : part2
 * fileName       : Cancellation
 * author         : LEE KYUHEON
 * date           : 2024-09-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-17        LEE KYUHEON       최초 생성
 */

//fun main(args: Array<String>) = runBlocking {
//    val job = launch(Dispatchers.Default) {
//        for (i in 1..10) {
//            if (!isActive) {
//                break
//            }
//            println("I'm sleeping $i ...")
//            Thread.sleep(500L)
//        }
//    }
//
//    delay(1300L)
//    println("main : I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main : Now I can quit.")
//}

//fun main(args: Array<String>) = runBlocking {
//    val job = launch {
//        try {
//            repeat(1000) { i ->
//                println("I'm sleeping $i ...")
//                delay(500L)
//            }
//        } finally {
//            println("main : I'm running finally!")
//        }
//    }
//
//    delay(1300L)
//    println("main : I'm tired of waiting!")
//    job.cancelAndJoin()
//    println("main : Now I can quit.")
//}

fun mainCancellation(args: Array<String>) = runBlocking {
    val job = launch {
        SleepingBed().use {
            it.sleep(1000)
        }
    }

    delay(1300L)
    println("main : I'm tired of waiting!")
    job.cancelAndJoin()
    println("main : Now I can quit.")
}

class SleepingBed : Closeable {

    suspend fun sleep(times: Int) {
        repeat(times) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    override fun close() {
        println("main : I'm running close() in SleepingBed!")
    }

}