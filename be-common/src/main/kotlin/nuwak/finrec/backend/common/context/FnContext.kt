package nuwak.finrec.backend.common.context

import nuwak.finrec.backend.common.model.IError
import nuwak.finrec.backend.common.model.PaginatedModel
import nuwak.finrec.backend.common.model.PortfolioIdModel
import nuwak.finrec.backend.common.model.PortfolioModel
import java.time.Instant

data class FnContext(
    var startTime : Instant = Instant.MIN,
    var operation: FnOperations = FnOperations.NONE,

    var onRequest: String = "",
    var requestPortfolioId: PortfolioIdModel = PortfolioIdModel.NONE,
    var requestPortfolio: PortfolioModel = PortfolioModel(),
    var responsePortfolio: PortfolioModel = PortfolioModel(),
    var requestPage: PaginatedModel = PaginatedModel(),
    var responsePage: PaginatedModel = PaginatedModel(),
    var responsePortfolios: MutableList<PortfolioModel> = mutableListOf(),
    var errors: MutableList<IError> = mutableListOf(),
    var status: CorStatus = CorStatus.STARTED,
)
{
    enum class FnOperations {
        NONE,
        INIT,
        CREATE,
        READ,
        UPDATE,
        DELETE,
        SEARCH,
        OFFER
    }

    /**
     * Добавляет ошибку в контекст
     *
     * @param error Ошибка, которую необходимо добавить в контекст
     * @param failingStatus Необходимо ли установить статус выполнения в FAILING (true/false)
     */
    fun addError(error: IError, failingStatus: Boolean = true) = apply {
        if (failingStatus) status = CorStatus.FAILING
        errors.add(error)
    }
}

