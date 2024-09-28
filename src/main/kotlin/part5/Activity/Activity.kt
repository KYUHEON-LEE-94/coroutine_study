package part5.Activity

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 *packageName    : part5.Activity
 * fileName       : Activity
 * author         : LEE KYUHEON
 * date           : 2024-09-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-28        LEE KYUHEON       최초 생성
 */
class Activity : CoroutineScope {
    lateinit var job: Job

    fun create() {
        job = Job()
    }

    fun destroy() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
}