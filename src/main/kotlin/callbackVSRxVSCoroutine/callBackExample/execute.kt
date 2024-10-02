package callbackVSRxVSCoroutine.callBackExample

import callbackVSRxVSCoroutine.callBackExample.dataSource.LocalUserSettingDataSource
import callbackVSRxVSCoroutine.callBackExample.dataSource.RemoteUserSettingDataSource
import callbackVSRxVSCoroutine.callBackExample.model.SampleViewModel

/**
 *packageName    : example1
 * fileName       : execute
 * author         : LEE KYUHEON
 * date           : 2024-10-01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-10-01        LEE KYUHEON       최초 생성
 */
class execute {
    fun main() {

        val viewModel = SampleViewModel(
            userSettingRepository = UserSettingRepository(
                localUserSettingDataSource = LocalUserSettingDataSource(),
                remoteUserSettingDataSource = RemoteUserSettingDataSource()
            )
        )

        viewModel.syncUserSetting()

        // Emulate user exit after 5 seconds
        Thread.sleep(5000)
        viewModel.onClear()
    }
}