package example1

/**
 * @Description : UserSetting.java
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
data class UserSetting(
    val userId: String,
    val primaryColor: String,
    val secondaryColor: String
){
    fun fold(userSetting: UserSetting): UserSetting {
        return UserSetting(userId, primaryColor, secondaryColor)
    }
}
