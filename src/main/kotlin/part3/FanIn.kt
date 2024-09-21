package part3

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part3
 * fileName       : FanIn
 * author         : LEE KYUHEON
 * date           : 2024-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-21        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    val channel = Channel<String> ()
    launch { sendString(channel, "Foo", 200L) }
    launch { sendString(channel, "Bar", 500L) }
    repeat(6) {
        println(channel.receive())
    }
    coroutineContext.cancelChildren()
}
suspend fun sendString(channel:SendChannel<String>, text:String, time:Long){
    while(true){
        delay(time)
        channel.send(text)
    }
}