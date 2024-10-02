package callbackVSRxVSCoroutine.callBackExample.util

/**
 * @Description : Logger.java
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
object Logger {
    fun d(message: String) {
        println("${Thread.currentThread().name.padEnd(40)}\t$message")
    }
}