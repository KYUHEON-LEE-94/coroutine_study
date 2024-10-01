package callbackVSRxVSCoroutine.coroutineExample.model


import callbackVSRxVSCoroutine.coroutineExample.UserSettingRepository
import callbackVSRxVSCoroutine.coroutineExample.util.Logger
import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

/**
 * @Description : SampleViewModel.java
 * @author      : heon
 * @since       : 2024-09-30
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-30       heon         최초 생성
 *
 * <pre>
 */
class SampleViewModel(private val userSettingRepository: UserSettingRepository) : CoroutineScope {

    override val coroutineContext: CoroutineContext = MockDispatchers.MAIN + SupervisorJob()
    private val userId = "TestUser#1"

    fun syncUserSetting() {
        Logger.d("SampleViewModel : syncUserSetting()")
        launch {
            Logger.d("SampleViewModel : syncUserSetting() start")
            val result = runCatching {
                withContext(Dispatchers.IO) {
                    userSettingRepository.syncUserSetting(userId)
                }
            }
            Logger.d("SampleViewModel : syncUserSetting() : result : $result")
        }
    }

    suspend fun onClear() {
        Logger.d("SampleViewModel : onClear()")
        coroutineContext[Job]?.cancelAndJoin()
    }
}

object MockDispatchers {
    val MAIN = Executors.newFixedThreadPool(1) { runnable ->
        Thread(runnable).apply {
            name = "main"
            isDaemon = true
        }
    }.asCoroutineDispatcher()
}