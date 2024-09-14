import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *packageName    :
 * fileName       : GlobalScopeLaunch
 * author         : LEE KYUHEON
 * date           : 2024-09-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-09-14        LEE KYUHEON       최초 생성
 */

    fun main(args: Array<String>){
        GlobalScope.launch {
            delay(1000L)
            print("World!")
        }

        println("Hello, ")
        Thread.sleep(2000L)

    }
