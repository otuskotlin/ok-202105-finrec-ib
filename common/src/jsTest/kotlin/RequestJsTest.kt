package nuwak.finrec.common.kmp

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RequestJsTest: StringSpec({
    "Check result" {
        Request().request() shouldBe "Some JS"
    }
})