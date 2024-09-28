package part3

import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 *packageName    : part3
 * fileName       : TickerChannels
 * author         : LEE KYUHEON
 * date           : 2024-09-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-22        LEE KYUHEON       최초 생성
 */
fun main(args: Array<String>) = runBlocking<Unit> {
    // create ticker channel
    val tickerChannel = ticker(
        delayMillis = 100, // 100ms 주기로 값이 생성
        initialDelayMillis = 0 // 첫 번째 값은 지연 없이 바로 생성
    )

    //1ms만 기다리고 값을 받는다. 첫번째는 바로 값을 받을 수 있음.
    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("Initial element is available immediately: $nextElement")

    // 50ms 동안만 기다리고 값을 받습니다.
    // 하지만 두 번째 값은 100ms 후에 도착하므로 50ms 안에 값이 준비되지 않아서 null이 반환
    nextElement = withTimeoutOrNull(50) { tickerChannel.receive() }
    println("Next element is not ready in 50 ms: $nextElement")

    //이번엔 withTimeoutOrNull(60)을 사용해 60ms 동안 기다립니다.
    // 두 번째 값은 100ms에 도착하므로 60ms 기다리면 값을 받을 수 있습니다.
    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("Next element is ready in 100 ms: $nextElement")

    // 소비자(consumer)가 300ms 동안 대기합니다.
    // 이 동안 ticker Channel은 값을 계속 생성하지만 소비하지 않고, 누적됩니다.
    println("Consumer pauses for 300ms")
    delay(300)

    // 소비자가 300ms 대기 후 다시 값을 받습니다.
    // 이미 300ms 동안 값이 누적되었기 때문에 값은 즉시 받을 수 있습니다.
    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("Next element is available immediately after large consumer delay: $nextElement")

    // 소비자가 다시 값을 받지만, 이번에는 60ms 안에 도착합니다.
    // 앞에서 300ms 지연이 발생했기 때문에 그 이후 값이 더 빨리 도착할 수 있습니다.
    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")

    tickerChannel.cancel()
}