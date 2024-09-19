package part2

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Description : HowToTimeoutMethodWork.java
 * @author      : heon
 * @since       : 2024-09-19
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-19       heon         최초 생성
 *
 * <pre>
 */
fun mainTimeout(args: Array<String>) = runBlocking {
    val job = launch {
        try {
            repeat(2) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("main : I'm running finally!")
        }
    }

    val timeoutJob = launch {
        delay(6000L)
        println("main : I'm tired of waiting. Cancel the job!")
        if (job.isActive) {
            job.cancelAndJoin()
        }
    }

    job.invokeOnCompletion { timeoutJob.cancel() }
}