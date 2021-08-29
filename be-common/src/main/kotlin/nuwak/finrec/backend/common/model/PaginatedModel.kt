package nuwak.finrec.backend.common.model

data class PaginatedModel(
    var size: Int = Int.MIN_VALUE,
    var lastId: PortfolioIdModel = PortfolioIdModel.NONE,
    var position: PositionModel = PositionModel.NONE,
) {
    enum class PositionModel {
        NONE,
        FIRST,
        MIDDLE,
        LAST;
    }
}
