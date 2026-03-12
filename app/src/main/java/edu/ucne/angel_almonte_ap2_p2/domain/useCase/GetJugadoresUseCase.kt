package edu.ucne.angel_almonte_ap2_p2.domain.useCase

import edu.ucne.angel_almonte_ap2_p2.domain.repository.JugadorRepository
import javax.inject.Inject

class GetJugadoresUseCase @Inject constructor(
    private val repository: JugadorRepository
) {
    operator fun invoke() = repository.getJugadores()
}