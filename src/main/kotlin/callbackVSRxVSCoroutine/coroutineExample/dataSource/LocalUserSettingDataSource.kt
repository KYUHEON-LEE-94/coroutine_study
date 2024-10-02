package callbackVSRxVSCoroutine.coroutineExample.dataSource

import callbackVSRxVSCoroutine.coroutineExample.UserSetting
import callbackVSRxVSCoroutine.coroutineExample.UserSettingDataSource
import callbackVSRxVSCoroutine.coroutineExample.util.Logger
import kotlinx.coroutines.delay


/**
 * @Description : LocalUserSettingDataSource.java
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
class LocalUserSettingDataSource : UserSettingDataSource {
    override suspend fun loadUserSetting(userId: String): UserSetting {
        Logger.d("LocalUserSettingDataSource : loadUserSetting()")
        delay(100)
        return UserSetting(
            userId = userId,
            primaryColor = "FFFF0000",
            secondaryColor = "FF0000FF"
        )
    }

    override suspend fun updateUserSetting(userSetting: UserSetting): UserSetting {
        Logger.d("LocalUserSettingDataSource : updateUserSetting()")
        delay(100)
        return userSetting
    }
}