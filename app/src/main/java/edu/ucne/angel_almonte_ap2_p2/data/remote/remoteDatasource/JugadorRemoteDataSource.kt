package edu.ucne.angel_almonte_ap2_p2.data.remote.remoteDatasource

import edu.ucne.angel_almonte_ap2_p2.data.remote.JugadoresAPi
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorDto
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import javax.inject.Inject

class JugadorRemoteDataSource @Inject constructor(
    private val api: JugadoresAPi
) {
    suspend fun getJugadores(): Result<List<jugadorDto>> {
        return try {
            val response = api.getJugadores()
            if (response.isSuccessful) Result.success(response.body() ?: emptyList())
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun getJugadorById(id: Int): Result<jugadorDto> {
        return try {
            val response = api.getJugadoresById(id)
            if (response.isSuccessful) Result.success(response.body()!!)
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun saveJugador(jugador: jugadorRequest): Result<jugadorDto> {
        return try {
            val response = api.postJugador(jugador)
            if (response.isSuccessful) Result.success(response.body()!!)
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }

    suspend fun updateJugador(id: Int, jugador: jugadorRequest): Result<Unit> {
        return try {
            val response = api.putJugador(id, jugador)
            if (response.isSuccessful) Result.success(Unit)
            else Result.failure(Exception("Error ${response.code()}"))
        } catch (e: Exception) { Result.failure(e) }
    }
}