package part6

import kotlinx.coroutines.*

/**
 * @Description : SupervisionScope.java
 * @author      : heon
 * @since       : 2024-10-07
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-10-07       heon         최초 생성
 *
 * <pre>
 */
fun main() = runBlocking {
    try {
        supervisorScope {
            val child = launch {
                try {
                    println("Child is sleeping")
                    delay(Long.MAX_VALUE)
                }finally {
                    println("child is cancelled")
                }
                yield()
                println("Throwing exception from scope")
                throw AssertionError()
            }
        }
    }catch(e: AssertionError) {
        println("Caught assertion error")
    }
}