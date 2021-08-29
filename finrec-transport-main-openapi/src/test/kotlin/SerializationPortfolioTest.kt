import com.fasterxml.jackson.databind.ObjectMapper
import nuwak.finrec.openapi.models.BaseMessage
import nuwak.finrec.openapi.models.CreatablePortfolio
import nuwak.finrec.openapi.models.CreatePortfolioRequest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SerializationPortfolioTest {
    private val jsonSerializer = ObjectMapper()
    private val dto = CreatePortfolioRequest(
        requestId = "12345",
        createPortfolio = CreatablePortfolio(
            name = "US",
            description = "American stocks",
            userId = 123
        )
    )

    @Test
    fun productDiscriminatorTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        assertContains(serializedString, Regex("userId\":\\s*123"))
    }

    @Test
    fun productDeserializationTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        val deserializedDto = jsonSerializer.readValue(serializedString, BaseMessage::class.java)
        assertEquals("American stocks", ((deserializedDto as CreatePortfolioRequest).createPortfolio?.description))
    }
}
