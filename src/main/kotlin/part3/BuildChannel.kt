package part3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

/**
 * @Description : BuildChannel.java
 * @author      : heon
 * @since       : 2024-09-20
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-20       heon         최초 생성
 *
 * <pre>
 */
fun main() = runBlocking {
    val squares = produceSqueres(5)
    squares.consumeEach { println(it) }
    println("Done")
}

fun CoroutineScope.produceSqueres(max:Int) : ReceiveChannel<Int> = produce{
    for (x in 1..max){
        send(x * x)
    }
}