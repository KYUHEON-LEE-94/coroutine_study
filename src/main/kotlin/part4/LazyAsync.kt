package part4

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * @Description : LazyAsync.java
 * @author      : heon
 * @since       : 2024-09-23
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-23       heon         최초 생성
 *
 * <pre>
 */
fun main() = runBlocking  {
    val time = measureTimeMillis {
        val one = async (start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async (start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }

        //If comment out below two lines, two coroutines will be called sequentially.
        one.start()
        two.start()

        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms")
}