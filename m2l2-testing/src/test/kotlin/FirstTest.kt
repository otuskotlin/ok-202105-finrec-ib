package nuwak.finrec.commonbackend

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FirstTest: StringSpec({
    "first Test Jvm" {
        1.toString() shouldBe "1"
    }
})