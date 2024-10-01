package callbackVSRxVSCoroutine.coroutineExample

import callbackVSRxVSCoroutine.coroutineExample.dataSource.LocalUserSettingDataSource
import callbackVSRxVSCoroutine.coroutineExample.dataSource.RemoteUserSettingDataSource
import callbackVSRxVSCoroutine.coroutineExample.util.Logger


/**
 * @Description : UserSettingRepository.java
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
class UserSettingRepository(
    private val localUserSettingDataSource: LocalUserSettingDataSource,
    private val remoteUserSettingDataSource: RemoteUserSettingDataSource
) {
    suspend fun syncUserSetting(userId: String): UserSetting {
        // Sync process
        Logger.d("UserSettingRepository : syncUserSetting() - Fetch from remote data source")
        val remoteUserSetting = remoteUserSettingDataSource.loadUserSetting(userId)
        Logger.d("UserSettingRepository : syncUserSetting() - Load from local data source")
        val localUserSetting = localUserSettingDataSource.loadUserSetting(userId)
        Logger.d("UserSettingRepository : syncUserSetting() - Sync and Store to local data source")
        val updatedUserSetting = localUserSetting.fold(remoteUserSetting)
        return localUserSettingDataSource.updateUserSetting(updatedUserSetting)
    }
}