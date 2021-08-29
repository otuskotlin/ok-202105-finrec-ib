import com.fasterxml.jackson.databind.ObjectMapper
import nuwak.finrec.openapi.models.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SerializationTest {
    private val requestId = "1234"
    private val createRequest = CreatePortfolioRequest(
        requestId = requestId,
        createPortfolio = CreatablePortfolio(
            name = "US stocks",
            description = "US stocks",
            totalVolume = "1001.00",
            userId = 123,
            visibility = PortfolioVisibility.OWNER_ONLY
        )
    )
    private val om = ObjectMapper()

    @Test
    fun serializationTest() {
        val json = om.writeValueAsString(createRequest)
        println(json)
        assertTrue("json must contain discriminator") {
            json.contains(""""messageType":"${createRequest::class.simpleName}"""")
        }
        assertTrue("json must serialize visibility field") {
            json.contains(""""visibility":"${PortfolioVisibility.OWNER_ONLY.value}"""")
        }
        assertTrue("json must serialize messageId field") {
            json.contains(""""requestId":"$requestId"""")
        }
        assertTrue("json must serialize totalVolume") {
            json.contains(""""totalVolume":"${createRequest.createPortfolio?.totalVolume}"""")
        }
    }

    @Test
    fun deserializeTest() {
        val json = om.writeValueAsString(createRequest)
        val deserialized = om.readValue(json, BaseMessage::class.java) as CreatePortfolioRequest

        assertEquals(PortfolioVisibility.OWNER_ONLY, deserialized.createPortfolio?.visibility)
        assertEquals(requestId, deserialized.requestId)
    }
}
