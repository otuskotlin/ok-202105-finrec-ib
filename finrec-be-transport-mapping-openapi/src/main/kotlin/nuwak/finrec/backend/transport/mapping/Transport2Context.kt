package nuwak.finrec.backend.transport.mapping

import nuwak.finrec.backend.common.context.FnContext
import nuwak.finrec.backend.common.model.*
import nuwak.finrec.openapi.models.*
import java.math.BigDecimal.ZERO


fun FnContext.setQuery(query: InitPortfolioRequest) = apply {
    operation = FnContext.FnOperations.INIT
    onRequest = query.requestId ?: ""
}

fun FnContext.setQuery(query: CreatePortfolioRequest) = apply {
    operation = FnContext.FnOperations.CREATE
    onRequest = query.requestId ?: ""
    requestPortfolio = query.createPortfolio?.toModel() ?: PortfolioModel()
}

fun FnContext.setQuery(query: ReadPortfolioRequest) = apply {
    operation = FnContext.FnOperations.READ
    onRequest = query.requestId ?: ""
    requestPortfolioId = PortfolioIdModel(query.readPortfolioId ?: 0)
}

fun FnContext.setQuery(query: UpdatePortfolioRequest) = apply {
    operation = FnContext.FnOperations.UPDATE
    onRequest = query.requestId ?: ""
    requestPortfolio = query.createPortfolio?.toModel() ?: PortfolioModel()
}

fun FnContext.setQuery(query: DeletePortfolioRequest) = apply {
    operation = FnContext.FnOperations.DELETE
    onRequest = query.requestId ?: ""
    requestPortfolioId = PortfolioIdModel(query.deletePortfolioId ?: 0)
}

fun FnContext.setQuery(query: SearchPortfolioRequest) = apply {
    operation = FnContext.FnOperations.SEARCH
    onRequest = query.requestId ?: ""
    requestPage = query.page?.toModel() ?: PaginatedModel()
}

private fun BasePaginatedRequest.toModel() = PaginatedModel(
    size = size ?: Int.MIN_VALUE,
    lastId = PortfolioIdModel(lastId ?: 0),
)

private fun CreatablePortfolio.toModel() = PortfolioModel(
    name = name ?: "",
    description = description ?: "",
    userId = UserIdModel(userId ?: 0),
    visibility = visibility?.let { PortfolioVisibilityModel.valueOf(it.name) } ?: PortfolioVisibilityModel.NONE,
    totalVolume = totalVolume?.toBigDecimal() ?: ZERO,
)

private fun UpdatablePortfolio.toModel() = PortfolioModel(
    id = PortfolioIdModel(id ?: 0),
    name = name ?: "",
    description = description ?: "",
    userId = UserIdModel(userId ?: 0),
    visibility = visibility?.let { PortfolioVisibilityModel.valueOf(it.name) } ?: PortfolioVisibilityModel.NONE,
    totalVolume = totalVolume?.toBigDecimal() ?: ZERO,
)


