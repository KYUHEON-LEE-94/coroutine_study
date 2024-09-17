package part1.extractFunction

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : extractFunction
 * fileName       : ligthWeight
 * author         : LEE KYUHEON
 * date           : 2024-09-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-15        LEE KYUHEON       최초 생성
 */
fun main(args: Array<String>) = runBlocking {
    repeat(100_000) {
        launch {
            delay(1000L)
            print(".")
        }
    }
}