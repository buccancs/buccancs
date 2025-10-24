package com.buccancs.domain.model

data class OrchestratorConfig(
    val host: String,
    val port: Int,
    val useTls: Boolean
) {
    init {
        require(
            host.isNotBlank()
        ) { "Host cannot be blank" }
        require(
            port in 1..65535
        ) { "Port must be within 1-65535" }
    }
}
