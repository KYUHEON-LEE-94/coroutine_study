package part5

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

/**
 * @Description : JobInContext.java
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
fun main() = runBlocking {
    println("My job is ${coroutineContext[Job]}")
}