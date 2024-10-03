package part6

import kotlinx.coroutines.*

/**
 *packageName    : part6
 * fileName       : CoroutineExceptionHandler
 * author         : LEE KYUHEON
 * date           : 2024-10-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-03        LEE KYUHEON       최초 생성
 */
fun main() = runBlocking {
    //CoroutineExceptionHandler는 코루틴이 취소되거나 실패할 때 호출
    /*CoroutineExceptionHandler는 CoroutineContext.Element의 한 요소로,
    특정 스레드나 코루틴이 예외를 던졌을 때 어떻게 처리할지를 정의*/
    val handler = CoroutineExceptionHandler() { _, exception ->
        println("Caught!! $exception")
    }
    val job = GlobalScope.launch (handler) {
        throw AssertionError()
    }
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException() // Nothing will be printed, relying on user to call deferred.await()
    }

    //job과 deferred가 종료될 때까지 대기합니다.
    // 이 코드는 두 개의 코루틴이 모두 완료될 때까지 현재 스레드를 블로킹
    joinAll(job, deferred)
}