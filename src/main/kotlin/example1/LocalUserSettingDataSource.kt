package example1

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
    override fun loadUserSetting(userId: String, callback: Callback<UserSetting>) {
        Thread.sleep(100)

        callback.onSuccess(
            UserSetting(
                userId = userId,
                primaryColor = "FFFF0000",
                secondaryColor = "FF0000FF"
            )
        )
    }

    override fun updateUserSetting(userSetting: UserSetting, callback: Callback<Unit>) {
        Thread.sleep(100)
        callback.onSuccess(Unit)
    }
}