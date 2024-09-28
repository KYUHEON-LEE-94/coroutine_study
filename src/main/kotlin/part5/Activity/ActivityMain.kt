package part5.Activity

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 *packageName    : part5.Activity
 * fileName       : ActivityMain
 * author         : LEE KYUHEON
 * date           : 2024-09-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-28        LEE KYUHEON       최초 생성
 */
class ActivityMain {

}

fun main() = runBlocking {
    val activity = Activity()
    activity.create()
    activity.doSomething()
    println("Launched coroutines")
    delay(500L) // delay for half a second
    println("Destroying activity!")
    activity.destroy() // cancels all coroutines
    delay(1000) // visually confirm that they don't work
}