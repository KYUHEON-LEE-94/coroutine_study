package part4

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Description : StructuredConcurrencyAsync.java
 * @author      : heon
 * @since       : 2024-09-25
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-25       heon         최초 생성
 *
 * <pre>
 */
fun main(args: Array<String>) = runBlocking {
    try {
        val time = measureTimeMillis {
            println("The answer is ${failedConcurrentSum()}")
        }
        println("Completed in $time ms")
    } catch (throwable: Throwable) {
        println("Computation failed with $throwable")
    }
}

suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async {

        try {
            delay(Long.MAX_VALUE)
            doSomethingUsefulOne()
        } finally {
            println("First child was canceled.")
        }
    }

    val two = async<Int> {
        println("Second child throw an exception.")
        doSomethingUsefulTwo()
        throw ArithmeticException("Exception on purpose.")
    }
    one.await() + two.await()
}