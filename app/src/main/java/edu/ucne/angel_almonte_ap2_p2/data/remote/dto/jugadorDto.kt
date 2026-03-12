package edu.ucne.angel_almonte_ap2_p2.data.remote.dto

import edu.ucne.angel_almonte_ap2_p2.domain.model.jugador

data class jugadorRequest(
    val nombres: String,
    val email: String
)

data class jugadorDto(
    val jugadorId: Int? = 0,
    val nombres: String? = "",
    val email: String? = ""
) {
    fun toDomain() = jugador(
        jugadorId = jugadorId ?: 0,
        nombres = nombres ?: "",
        email = email ?: ""
    )
}