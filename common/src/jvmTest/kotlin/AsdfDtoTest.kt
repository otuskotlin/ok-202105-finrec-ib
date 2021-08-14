package nuwak.finrec.common.kmp

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class AsdfDtoTest: StringSpec({
    "first Test Jvm" {
        1.toString() shouldBe "1"
    }

    "Test AsdfDto" {
        forAll(
            row("фыва"),
            row("asdf"),
            row("fyva")
        ) { fyva ->
            val dto = AsdfDto(fyva)
             dto.fyva shouldBe fyva
        }
    }
})