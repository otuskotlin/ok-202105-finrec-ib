package nuwak.finrec.common.kmp

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class RequestTest: StringSpec({
    "Check result" {
        Request().request() shouldBe "Some JVM"
    }
})