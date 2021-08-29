package nuwak.finrec.backend.transport.mapping

import nuwak.finrec.backend.common.context.FnContext
import nuwak.finrec.backend.common.model.*
import nuwak.finrec.openapi.models.*

fun FnContext.toInitResponse() = InitPortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) InitPortfolioResponse.Result.SUCCESS
    else InitPortfolioResponse.Result.ERROR
)

fun FnContext.toReadResponse() = ReadPortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    readPortfolio = responsePortfolio.takeIf { it != PortfolioModel() }?.toTransport(),
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    result = if (errors.find { it.level == IError.Level.ERROR } == null) ReadPortfolioResponse.Result.SUCCESS
    else ReadPortfolioResponse.Result.ERROR
)

fun FnContext.toCreateResponse() = CreatePortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    createdPortfolio = responsePortfolio.takeIf { it != PortfolioModel() }?.toTransport(),
    result = if (errors.find { it.level == IError.Level.ERROR } == null) CreatePortfolioResponse.Result.SUCCESS
    else CreatePortfolioResponse.Result.ERROR
)

fun FnContext.toUpdateResponse() = UpdatePortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    updatedPortfolio = responsePortfolio.takeIf { it != PortfolioModel() }?.toTransport(),
    result = if (errors.find { it.level == IError.Level.ERROR } == null) UpdatePortfolioResponse.Result.SUCCESS
    else UpdatePortfolioResponse.Result.ERROR
)

fun FnContext.toDeleteResponse() = DeletePortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    deletedPortfolio = responsePortfolio.takeIf { it != PortfolioModel() }?.toTransport(),
    result = if (errors.find { it.level == IError.Level.ERROR } == null) DeletePortfolioResponse.Result.SUCCESS
    else DeletePortfolioResponse.Result.ERROR
)

fun FnContext.toSearchResponse() = SearchPortfolioResponse(
    requestId = onRequest.takeIf { it.isNotBlank() },
    errors = errors.takeIf { it.isNotEmpty() }?.map { it.toTransport() },
    foundPortfolios = responsePortfolios.takeIf { it.isNotEmpty() }?.filter { it != PortfolioModel() }
        ?.map { it.toTransport() },
    page = responsePage.takeIf { it != PaginatedModel() }?.toTransport(),
    result = if (errors.find { it.level == IError.Level.ERROR } == null) SearchPortfolioResponse.Result.SUCCESS
    else SearchPortfolioResponse.Result.ERROR
)

private fun PaginatedModel.toTransport() = BasePaginatedResponse(
    size = size.takeIf { it != Int.MIN_VALUE },
    lastId = lastId.takeIf { it != PortfolioIdModel.NONE }?.id,
    position = position.takeIf { it != PaginatedModel.PositionModel.NONE }
        ?.let { BasePaginatedResponse.Position.valueOf(it.name) }
)

private fun IError.toTransport() = RequestError(
    message = message.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
)

private fun PortfolioModel.toTransport() = ResponsePortfolio(
    id = id.takeIf { it != PortfolioIdModel.NONE }?.id,
    name = name.takeIf { it.isNotBlank() },
    description = description.takeIf { it.isNotBlank() },
    userId = userId.takeIf { it != UserIdModel.NONE }?.asString(),
    visibility = visibility.takeIf { it != PortfolioVisibilityModel.NONE }
        ?.let { PortfolioVisibility.valueOf(it.name) },
//todo: добавить assets
    permissions = permissions.takeIf { it.isNotEmpty() }?.filter { it != PermissionModel.NONE }
        ?.map { PortfolioPermissions.valueOf(it.name) }?.toSet(),
)
