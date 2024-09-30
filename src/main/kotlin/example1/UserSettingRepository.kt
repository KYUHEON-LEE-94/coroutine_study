package example1

import example1.util.Callback
import example1.util.Disposable
import example1.util.Logger
import example1.util.asDisposable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.coroutines.cancellation.CancellationException

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
    private val dispatcher: ExecutorService = Executors.newCachedThreadPool { runnable ->
        Thread(runnable).apply {
            isDaemon = true
        }
    }

    fun syncUserSetting(userId: String, callback: Callback<UserSetting>): Disposable {
        // Sync process
        Logger.d("UserSettingRepository : syncUserSetting() - Fetch from remote data source")
        return dispatcher.submit {
            remoteUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                override fun onSuccess(value: UserSetting) {
                    if (Thread.interrupted()) {
                        callback.onFail(CancellationException())
                        return
                    }

                    val remoteUserSetting = value

                    Logger.d("UserSettingRepository : syncUserSetting() - Load from local data source")
                    localUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                        override fun onSuccess(value: UserSetting) {
                            if (Thread.interrupted()) {
                                callback.onFail(CancellationException())
                                return
                            }

                            val localUserSetting = value
                            val updatedUserSetting = localUserSetting.fold(remoteUserSetting)

                            Logger.d("UserSettingRepository : syncUserSetting() - Update to local data source")
                            localUserSettingDataSource.updateUserSetting(
                                updatedUserSetting,
                                object : Callback<Unit> {
                                    override fun onSuccess(value: Unit) {
                                        if (Thread.interrupted()) {
                                            callback.onFail(CancellationException())
                                            return
                                        }

                                        Logger.d("UserSettingRepository : syncUserSetting() - Success")
                                        callback.onSuccess(updatedUserSetting)
                                    }

                                    override fun onFail(throwable: Throwable) {
                                        Logger.d("UserSettingRepository : syncUserSetting() - Failed : $throwable")
                                        callback.onFail(throwable)
                                    }

                                })
                        }

                        override fun onFail(throwable: Throwable) {
                            Logger.d("UserSettingRepository : syncUserSetting() - Failed : $throwable")
                            callback.onFail(throwable)
                        }
                    })
                }

                override fun onFail(throwable: Throwable) {
                    Logger.d("UserSettingRepository : syncUserSetting() - Failed : $throwable")
                    callback.onFail(throwable)
                }

            })
        }.asDisposable()
    }
}