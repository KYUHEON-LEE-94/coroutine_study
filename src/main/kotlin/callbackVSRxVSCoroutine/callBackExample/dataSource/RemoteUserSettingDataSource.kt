package callbackVSRxVSCoroutine.callBackExample.dataSource

import callbackVSRxVSCoroutine.callBackExample.UserSetting
import callbackVSRxVSCoroutine.callBackExample.UserSettingDataSource
import callbackVSRxVSCoroutine.callBackExample.util.Callback

/**
 * @Description : RemoteUserSettingDataSource.java
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
class RemoteUserSettingDataSource: UserSettingDataSource {
    override fun loadUserSetting(userId: String, callback: Callback<UserSetting>) {
        Thread.sleep(200)

        callback.onSuccess(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF00FF00"
            )
        )
    }

    override fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>) {
        throw UnsupportedOperationException()
    }
}