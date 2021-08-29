package nuwak.finrec.backend.common.model

interface IError {
    var field: String
    var level: Level
    var message: String
    val exception: Throwable

    enum class Level {
        ERROR,
        WARNING
    }
}
