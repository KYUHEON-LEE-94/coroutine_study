package part1.Scopebuilder

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : Scopebuilder
 * fileName       : coroutineScope
 * author         : LEE KYUHEON
 * date           : 2024-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-14        LEE KYUHEON       최초 생성
 */
fun main(args: Array<String>) = runBlocking {

    coroutineScope {
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope")
    }

    launch {
        delay(200L)
        println("Task from runBlocking")
    }


    println("Coroutine scope is over")
}