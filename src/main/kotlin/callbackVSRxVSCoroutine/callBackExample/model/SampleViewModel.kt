package callbackVSRxVSCoroutine.callBackExample.model

import callbackVSRxVSCoroutine.callBackExample.UserSetting
import callbackVSRxVSCoroutine.callBackExample.UserSettingRepository
import callbackVSRxVSCoroutine.callBackExample.util.Callback
import callbackVSRxVSCoroutine.callBackExample.util.Disposable
import callbackVSRxVSCoroutine.callBackExample.util.Logger

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
class SampleViewModel(private val userSettingRepository: UserSettingRepository) {

    private val userId = "TestUser#1"

    private var activeSyncDisposable: Disposable? = null

    fun syncUserSetting() {
        Logger.d("SampleViewModel : syncUserSetting() - start")
        activeSyncDisposable = userSettingRepository.syncUserSetting(userId, object : Callback<UserSetting> {
            override fun onSuccess(value: UserSetting) {
                Logger.d("SampleViewModel : syncUserSetting() : success : $value")
            }

            override fun onFail(throwable: Throwable) {
                Logger.d("SampleViewModel : syncUserSetting() : failed : $throwable")
            }

        })
    }

    fun onClear() {
        Logger.d("SampleViewModel : onClear()")
        activeSyncDisposable?.dispose()
        activeSyncDisposable = null
    }
}