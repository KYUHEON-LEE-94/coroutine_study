import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

/**
 * @Description : KotestTest.java
 * @author      : heon
 * @since       : 2024-09-24
 *
 * <pre>
 *
 * << 개정이력(Modification Information) >>
 *
 * ===========================================================
 * 수정인          수정자      수정내용
 * ----------    ----------    --------------------
 * 2024-09-24       heon         최초 생성
 *
 * <pre>
 */
class KotestTest: StringSpec ({

    "1 + 1 should equal 2"{
        val result = 1 + 1
        result shouldBe 2
    }

    "String length should return correct length" {
        "Kotest".length shouldBe 6
    }

})