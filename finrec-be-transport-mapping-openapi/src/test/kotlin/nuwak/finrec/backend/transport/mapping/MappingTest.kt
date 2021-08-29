package nuwak.finrec.backend.transport.mapping

import nuwak.finrec.backend.common.context.FnContext
import nuwak.finrec.backend.common.model.*
import nuwak.finrec.openapi.models.PortfolioVisibility
import nuwak.finrec.openapi.models.UpdatablePortfolio
import nuwak.finrec.openapi.models.UpdatePortfolioRequest
import nuwak.finrec.openapi.models.UpdatePortfolioResponse
import kotlin.test.Test
import kotlin.test.assertEquals

class MappingTest {

    @Test
    fun setUpdateQueryMappingTest() {
        val query = UpdatePortfolioRequest(
            requestId = "12345",
            createPortfolio = UpdatablePortfolio(
                id = 123,
                name = "name-1",
                description = "description-1",
                userId = 321,
                visibility = PortfolioVisibility.REGISTERED_ONLY,
            )
        )
        val context = FnContext().setQuery(query)
        assertEquals("12345", context.onRequest)
        assertEquals(123, context.requestPortfolio.id.id)
        assertEquals("name-1", context.requestPortfolio.name)
        assertEquals("description-1", context.requestPortfolio.description)
        assertEquals(321, context.requestPortfolio.userId.id)
        assertEquals(PortfolioVisibilityModel.REGISTERED_ONLY, context.requestPortfolio.visibility)
    }

    @Test
    fun updateResponseMappingTest() {
        val context = FnContext(
            onRequest = "12345",
            responsePortfolio = PortfolioModel(
                id = PortfolioIdModel(123),
                name = "name-1",
                description = "description-1",
                userId = UserIdModel(321),
                visibility = PortfolioVisibilityModel.REGISTERED_ONLY,
            ),
            errors = mutableListOf(CommonErrorModel(level = IError.Level.WARNING)),
        )
        val response = context.toUpdateResponse()
        assertEquals("12345", response.requestId)
        assertEquals(123, response.updatedPortfolio?.id)
        assertEquals("name-1", response.updatedPortfolio?.name)
        assertEquals("description-1", response.updatedPortfolio?.description)
        assertEquals(321, response.updatedPortfolio?.userId)
        assertEquals(PortfolioVisibility.REGISTERED_ONLY, response.updatedPortfolio?.visibility)
        assertEquals(UpdatePortfolioResponse.Result.SUCCESS, response.result)
        assertEquals(1, response.errors?.size)
    }
}
