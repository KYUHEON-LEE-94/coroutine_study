package part5

import kotlinx.coroutines.*

/**
 *packageName    : part5
 * fileName       : ThreadLocalData
 * author         : LEE KYUHEON
 * date           : 2024-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-29        LEE KYUHEON       최초 생성
 */
val threadLocal = ThreadLocal<String?>()

fun main() = runBlocking {

    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")

        withContext(threadLocal.asContextElement(value = "child")) {
            println("Child : Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            println("Child : After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
    }
    job.join()
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
}