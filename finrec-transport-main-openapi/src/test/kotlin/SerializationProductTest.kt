import com.fasterxml.jackson.databind.ObjectMapper
import nuwak.finrec.openapi.models.BaseMessage
import nuwak.finrec.openapi.models.CreatablePortfolio
import nuwak.finrec.openapi.models.CreatePortfolioRequest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SerializationProductTest {
    private val jsonSerializer = ObjectMapper()
    val dto = CreatePortfolioRequest(
        requestId = "12345",
        createPortfolio = CreatablePortfolio(
            name = "Bolt",
            description = "Strong Bolt",
            userId = 123
        )
    )

    @Test
    fun productDiscriminatorTest() {
        val serializedString = jsonSerializer.writeValueAsString(dto)
        assertContains(serializedString, Regex("userId\":\\s*123"))
    }

//    @Test
//    fun productDeserializationTest() {
//        val serializedString = jsonSerializer.writeValueAsString(dto)
//        val deserializedDto = jsonSerializer.readValue(serializedString, BaseMessage::class.java)
//        assertEquals(32.0, ((deserializedDto as CreateAdRequest).createAd?.product as AdProductBolt).lengh)
//    }
}
