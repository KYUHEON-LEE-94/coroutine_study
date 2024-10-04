package part6

import kotlinx.coroutines.*
import java.io.IOException

/**
 * @Description : ExceptionAggregation.java
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
    val handler = CoroutineExceptionHandler{_, exception ->
        println("Caught $exception with suppressed ${exception.suppressed.contentToString()}")
    }

    val job = GlobalScope.launch(handler) {
        launch{
            try {
                delay(Long.MAX_VALUE)
            }finally {
                throw ArithmeticException()
            }
        }

        launch {
            delay(100)
            throw IOException()
        }

        delay(Long.MAX_VALUE)
    }

    job.join()
}