package edu.ucne.angel_almonte_ap2_p2.data.remote.dto

import edu.ucne.angel_almonte_ap2_p2.domain.model.Deposito

data class JugadorRequest(
    val nombres: String,
    val email: String

)
data class JugadorDto(

) {
    fun toDomain() = Deposito(

    )
}