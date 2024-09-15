package test

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : test
 * fileName       : GlobalTest
 * author         : LEE KYUHEON
 * date           : 2024-09-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-15        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    GlobalScope.launch {
        println("Starting long-running task")
        delay(5000L)
        println("Task finished")
    }
    delay(1000L)
    println("Main function finished")
}