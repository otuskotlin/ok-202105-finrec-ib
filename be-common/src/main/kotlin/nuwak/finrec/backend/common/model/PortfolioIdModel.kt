package nuwak.finrec.backend.common.model

@JvmInline
value class PortfolioIdModel(val id: Long) {
    companion object {
        val NONE = PortfolioIdModel(0)
    }
}
