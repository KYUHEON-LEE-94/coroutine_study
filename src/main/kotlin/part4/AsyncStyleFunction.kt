package part4

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Description : AsyncStyleFunction.java
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

fun main() {
    val time = measureTimeMillis {
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()

        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }

    }
    println("Completed in $time ms")

}


    fun somethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}


