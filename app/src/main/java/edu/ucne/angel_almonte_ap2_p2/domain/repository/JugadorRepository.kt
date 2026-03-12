package edu.ucne.angel_almonte_ap2_p2.domain.repository

import edu.ucne.angel_almonte_ap2_p2.data.remote.Resource
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import edu.ucne.angel_almonte_ap2_p2.domain.model.jugador
import kotlinx.coroutines.flow.Flow

interface JugadorRepository {
    fun getJugadores(): Flow<Resource<List<jugador>>>
    fun getJugadorById(id: Int): Flow<Resource<jugador>>
    fun saveJugador(jugador: jugadorRequest): Flow<Resource<jugador>>
    fun updateJugador(id: Int, jugador: jugadorRequest): Flow<Resource<Unit>>
}