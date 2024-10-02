import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.asContextElement
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
class ThreadLocalTest: StringSpec ({



    "Simple Thread Local Test"{
        val globalVariable = ThreadLocal.withInitial { 0 }

        println("Main before set : ${globalVariable.get()}")
        globalVariable.set(-1)
        println("Main after set : ${globalVariable.get()}")

        val thread1 = Thread {
            println("Thread1 before set : ${globalVariable.get()}")
            globalVariable.set(1)
            println("Thread1 after set : ${globalVariable.get()}")
        }.also { it.start() }

        val thread2 = Thread {
            println("Thread2 before set : ${globalVariable.get()}")
            globalVariable.set(2)
            println("Thread2 after set : ${globalVariable.get()}")
        }.also { it.start() }

        thread1.join()
        thread2.join()
        println("Main after threads : ${globalVariable.get()}")
    }



    "ThreadLocal<String>을 정의하고 기본 값으로 “Default” 를 지정"{
        val globalVariable = ThreadLocal.withInitial { "Default" }

        launch {
            println("Launch1 before set : ThreadLocal : ${globalVariable.get()} ")
            globalVariable.set("Launch1")
            println("Launch1 after set : ThreadLocal : ${globalVariable.get()} ")
        }.join()

        launch {
            println("Launch2 before set : ThreadLocal : ${globalVariable.get()} ")
            globalVariable.set("Launch2")
            println("Launch2 after set : ThreadLocal : ${globalVariable.get()} ")
        }
    }

    "When Coroutine suspended ThreadlLocal data is resumed"{
        val globalVariable = ThreadLocal.withInitial { "Default" }

        runBlocking {

            launch {
                println("Thread ${Thread.currentThread().name}, before set, ThreadLocal : ${globalVariable.get()}")
                globalVariable.set("Launch")
                println("Thread ${Thread.currentThread().name}, after set, ThreadLocal : ${globalVariable.get()}")
            }.join()

            println("Thread ${Thread.currentThread().name}, after launch block, ThreadLocal : ${globalVariable.get()}")
        }
    }

    "ThreadLocal to CoroutineLocal"{
        val globalVariable = ThreadLocal.withInitial { "Default" }

        runBlocking {

            launch(globalVariable.asContextElement()) {
                println("Thread ${Thread.currentThread().name}, before set, ThreadLocal : ${globalVariable.get()}")
                globalVariable.set("Launch")
                println("Thread ${Thread.currentThread().name}, after set, ThreadLocal : ${globalVariable.get()}")
            }.join()

            println("Thread ${Thread.currentThread().name}, after launch block, ThreadLocal : ${globalVariable.get()}")
        }
    }
})