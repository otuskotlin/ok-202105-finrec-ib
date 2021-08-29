package nuwak.finrec.backend.common.model

import java.math.BigDecimal
import java.math.BigDecimal.ZERO

data class PortfolioModel(
    var id: PortfolioIdModel = PortfolioIdModel.NONE,
    var name: String = "",
    var description: String = "",
    var totalVolume: BigDecimal = ZERO,
    var assets: MutableSet<Asset> = mutableSetOf(),
    var userId: UserIdModel = UserIdModel.NONE,
    var visibility: PortfolioVisibilityModel = PortfolioVisibilityModel.NONE,
    var permissions: MutableSet<PermissionModel> = mutableSetOf(),
)
