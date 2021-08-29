package nuwak.finrec.backend.common.model

import java.math.BigDecimal

data class Asset(
    val ticker: Ticker,
    val volume: BigDecimal,
)
