package callbackVSRxVSCoroutine.coroutineExample.dataSource

import callbackVSRxVSCoroutine.coroutineExample.UserSetting
import callbackVSRxVSCoroutine.coroutineExample.UserSettingDataSource
import callbackVSRxVSCoroutine.coroutineExample.util.Logger
import kotlinx.coroutines.delay


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
class RemoteUserSettingDataSource : UserSettingDataSource {
    override suspend fun loadUserSetting(userId: String): UserSetting {
        Logger.d("RemoteUserSettingDataSource : loadUserSetting()")
        delay(200)
        return UserSetting(
            userId = userId,
            primaryColor = "FFFF0000",
            secondaryColor = "FF00FF00"
        )
    }

    override suspend fun updateUserSetting(userSetting: UserSetting): UserSetting {
        throw UnsupportedOperationException()
    }
}