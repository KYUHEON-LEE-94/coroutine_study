package part6

import kotlinx.coroutines.*

/**
 * @Description : SupervisionJob.java
 * @author      : heon
 * @since       : 2024-10-04
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-10-04       heon         최초 생성
 *
 * <pre>
 */
fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)){
        //first child  - 예외 무시되는 샘플
        val firstChild = launch( CoroutineExceptionHandler{_, _ ->}) {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }

        //second child 시작
        val secondChild = launch {
            firstChild.join()
        }

        println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")

        try {
            delay(Long.MAX_VALUE)
        } finally {
            // But cancellation of the supervisor is propagated
            println("Second child is cancelled because supervisor is cancelled")
        }

        // wait until the first child fails & completes
        firstChild.join()
        println("Cancelling supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}