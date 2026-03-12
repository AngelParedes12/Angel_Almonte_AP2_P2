package edu.ucne.angel_almonte_ap2_p2.data.repository

import edu.ucne.angel_almonte_ap2_p2.data.remote.Resource
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import edu.ucne.angel_almonte_ap2_p2.data.remote.remoteDatasource.JugadorRemoteDataSource
import edu.ucne.angel_almonte_ap2_p2.domain.model.jugador
import edu.ucne.angel_almonte_ap2_p2.domain.repository.JugadorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JugadorRepositoryImpl @Inject constructor(
    private val dataSource: JugadorRemoteDataSource
) : JugadorRepository {

    override fun getJugadores(): Flow<Resource<List<jugador>>> = flow {
        emit(Resource.Loading())
        val response = dataSource.getJugadores()
        response.onSuccess { jugadores ->
            emit(Resource.Success(jugadores.map { it.toDomain() }))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun getJugadorById(id: Int): Flow<Resource<jugador>> = flow {
        emit(Resource.Loading())
        val response = dataSource.getJugadorById(id)
        response.onSuccess { jugador ->
            emit(Resource.Success(jugador.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun saveJugador(jugador: jugadorRequest): Flow<Resource<jugador>> = flow {
        emit(Resource.Loading())
        val response = dataSource.saveJugador(jugador)
        response.onSuccess { result ->
            emit(Resource.Success(result.toDomain()))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }

    override fun updateJugador(id: Int, jugador: jugadorRequest): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        val response = dataSource.updateJugador(id, jugador)
        response.onSuccess {
            emit(Resource.Success(Unit))
        }.onFailure {
            emit(Resource.Error(it.message ?: "Error desconocido"))
        }
    }
}