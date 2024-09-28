package part5

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part5
 * fileName       : NamingCoroutineOfDebug
 * author         : LEE KYUHEON
 * date           : 2024-09-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-28        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    log("Started main coroutine")

    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log("computing v1")
        252
    }

    val v2 = async(CoroutineName("v12oroutine")) {
        delay(1000)
        log("Computing v2")
        6
    }

    log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
}