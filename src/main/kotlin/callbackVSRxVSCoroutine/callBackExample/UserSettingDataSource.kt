package callbackVSRxVSCoroutine.callBackExample

import callbackVSRxVSCoroutine.callBackExample.util.Callback


/**
 * @Description : UserSettingDataSource.java
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
interface UserSettingDataSource {
    fun loadUserSetting(userId: String, callback: Callback<UserSetting>)
    fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>)
}