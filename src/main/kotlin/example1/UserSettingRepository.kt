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
    /**
     * syncUserSetting은 userId와 callback을 매개변수로 받아, 비동기적으로 원격 데이터를 가져오고 로컬 데이터를 업데이트하는 작업을 실행.
     * 반환값은 Disposable 객체로, 이 작업을 취소할 수 있도록 도와줍니다.
     * **/
    fun syncUserSetting(userId: String, callback: Callback<UserSetting>): Disposable {

        Logger.d("UserSettingRepository : syncUserSetting() - Fetch from remote data source")
        // .submit 메서드를 호출하여 새로운 작업을 쓰레드에서 실행하도록 요청
        return dispatcher.submit {
            //원격 데이터 소스에서 사용자 설정을 가져오는 remoteUserSettingDataSource.loadUserSetting을 실행
            remoteUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                // 1. 성공한 경우
                override fun onSuccess(value: UserSetting) {

                    if (Thread.interrupted()) {
                        callback.onFail(CancellationException())
                        return
                    }

                    val remoteUserSetting = value

                    Logger.d("UserSettingRepository : syncUserSetting() - Load from local data source")
                    // 원격 데이터 소스를 가져온 후, 로컬 데이터 소스에서 동일한 userId에 대한 사용자 설정을 load
                    localUserSettingDataSource.loadUserSetting(userId, object : Callback<UserSetting> {
                        override fun onSuccess(value: UserSetting) {

                            if (Thread.interrupted()) {
                                callback.onFail(CancellationException())
                                return
                            }

                            val localUserSetting = value
                            //로컬 데이터가 성공적으로 로드되면, 원격에서 받은 데이터와 로컬 데이터를 fold 함수를 이용해 병합
                            val updatedUserSetting = localUserSetting.fold(remoteUserSetting)

                            Logger.d("UserSettingRepository : syncUserSetting() - Update to local data source")
                            // 병합된 사용자 설정은 다시 로컬 데이터 소스에 업데이트
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
                // 2. 실패한 경우
                override fun onFail(throwable: Throwable) {
                    Logger.d("UserSettingRepository : syncUserSetting() - Failed : $throwable")
                    callback.onFail(throwable)
                }

            })
        }.asDisposable()
    }
}