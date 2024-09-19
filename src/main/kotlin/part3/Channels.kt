package part3

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @Description : Channels.java
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
fun main(args: Array<String>) = runBlocking{
    val channel = Channel<Int>()
    launch {
        // this might be heavy CPU-consuming computation or async logic, we'll just send five squares
        for (x in 1..5) channel.send(x * x)
    }
// here we print five received integers:
    repeat(5) { println(channel.receive()) }
    println("Done!")
}