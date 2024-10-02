import io.kotest.core.spec.style.StringSpec
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Description : KotestTest.java
 * @author      : heon
 * @since       : 2024-09-24
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-24       heon         최초 생성
 *
 * <pre>
 */
class `WhyCoroutineLightWeightThread`: StringSpec ({



    "thread인 경우"{
        val threads = mutableListOf<Thread>()
        println("처음 : ${Thread.activeCount()}")
        repeat(1000) {
            threads += Thread {
                Thread.sleep(1000)
            }.also {
                it.start()
            }
        }
        println("끝 : ${Thread.activeCount()}")
    }



    "coroutine인 경우"{
        val jobs = mutableListOf<Job>()
        println("처음 : ${Thread.activeCount()}")
        repeat(1000) {
            jobs += launch {
                delay(1000)
            }
        }
        println("끝 : ${Thread.activeCount()}")
    }

})