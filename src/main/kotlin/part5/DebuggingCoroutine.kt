package part5

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * @Description : DebuggingCoroutine.java
 * @author      : heon
 * @since       : 2024-09-26
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-26       heon         최초 생성
 *
 * <pre>
 */
fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking {
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}")
}