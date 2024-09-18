package sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : sample
 * fileName       : Timer
 * author         : LEE KYUHEON
 * date           : 2024-09-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-18        LEE KYUHEON       최초 생성
 */
fun main () = runBlocking {
    val job = launch {
        startCountdown(3)
    }

    job.join()
}

suspend fun  startCountdown(seconds: Int){
    for (i in seconds downTo 1){
        println("Timer: $i remaining")
        delay(1000L)
    }
    println("Time's up")
}

