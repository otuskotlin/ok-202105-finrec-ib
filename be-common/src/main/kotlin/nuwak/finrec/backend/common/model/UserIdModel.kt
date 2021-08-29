package nuwak.finrec.backend.common.model

@JvmInline
value class UserIdModel(val id: Long) {
    companion object {
        val NONE = UserIdModel(0)
    }

    fun asString() = id
}
