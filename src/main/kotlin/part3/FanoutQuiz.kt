package part3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part3
 * fileName       : FanoutQuiz
 * author         : LEE KYUHEON
 * date           : 2024-09-21
 * description    :
 * 여러 개의 센서가 실시간으로 데이터를 측정하고 있습니다.
 * 각 센서는 초당 한 번씩 온도 데이터를 생성하고, 이 데이터는 여러 프로세서가 나누어 처리해야 합니다.
 * 각 프로세서는 온도 데이터를 받고, 해당 데이터가 섭씨 30도 이상일 때만 "경고 메시지"를 출력해야 합니다.
 *  총 3개의 센서가 각각 1초마다 온도를 생성합니다.
 *
 *  4개의 프로세서가 이 데이터를 처리하며,
 * 섭씨 30도 이상인 데이터에 대해서만 println("경고: $temperature 도!")를 출력해야 합니다.
 * 5초 동안 데이터가 처리된 후, 모든 프로세서가 종료됩니다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-21        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    val producer = produceTemperature()
    repeat(3){
        checkTemperature(producer)
    }
    delay(5000L)
    producer.cancel()
}

fun CoroutineScope.produceTemperature() = produce{
    while (true) {
        val temperature = (20..40).random() // 20에서 40 사이의 무작위 온도 생성
        send(temperature) // 생성된 온도값을 채널로 전송
        delay(1000L)
    }
}

fun CoroutineScope.checkTemperature(channel: ReceiveChannel<Int>) = launch {
    for (temperature in channel) {
        if(temperature > 30){
            println("경고: $temperature 도!")
        }else {
            println("정상 온도: $temperature 도")
        }
    }
}