package part3

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part3
 * fileName       : FairChannel
 * author         : LEE KYUHEON
 * date           : 2024-09-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-22        LEE KYUHEON       최초 생성
 */
data class Ball(var hits: Int)

fun main()  = runBlocking {
    val table = Channel<Ball>()

    launch{ player("ping", table) }
    launch{ player("pong", table) }

    table.send(Ball(0))
    delay(1000)
    coroutineContext.cancelChildren()

}

suspend fun player(name:String, table:Channel<Ball>){
    for(ball in table){
        ball.hits ++
        println("$name $ball")
        // Comment out below delay to see the fairness a bit more.
        delay(300)
        table.send(ball)
    }
}