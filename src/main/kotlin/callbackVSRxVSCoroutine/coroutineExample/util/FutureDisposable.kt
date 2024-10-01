package callbackVSRxVSCoroutine.coroutineExample.util

import java.util.concurrent.Future

/**
 * @Description : FutureDisposable.java
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

class FutureDisposable(private val future: Future<*>) : Disposable {
    override fun dispose() {
        if (future.isCancelled.not()) {
            future.cancel(true)
        }
    }
}

fun Future<*>.asDisposable(): Disposable {
    return FutureDisposable(this)
}