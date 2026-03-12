package edu.ucne.angel_almonte_ap2_p2.domain.useCase

import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import edu.ucne.angel_almonte_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class SaveJugadorUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke(jugador: jugadorRequest) = repository.saveJugador(jugador)
}