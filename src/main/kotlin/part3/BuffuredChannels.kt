package part3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part3
 * fileName       : BuffuredChannels
 * author         : LEE KYUHEON
 * date           : 2024-09-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-21        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    val channel = Channel<Int>(4)

    val sender = launch{
        repeat(10){
            print("Try to send $it : ")
            channel.send(it)
            //delay(50L)
            //receive(channel)
            println("Done")
        }

    }
    delay(1000)
    sender.cancel()
}

fun CoroutineScope.receive(channel: ReceiveChannel<Int>) = launch{
    channel.consumeEach {
        println("received $it")
    }
}