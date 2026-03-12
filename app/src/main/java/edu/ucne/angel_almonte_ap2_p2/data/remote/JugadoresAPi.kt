package edu.ucne.angel_almonte_ap2_p2.data.remote

import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorDto
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JugadoresAPi {
    @GET("api/Jugadores")
    suspend fun getJugadores(): Response<List<jugadorDto>>

    @GET("api/Jugadores/{id}")
    suspend fun getJugadoresById(@Path("id") id: Int): Response<jugadorDto>

    @POST("api/Jugadores")
    suspend fun postJugador(@Body jugador: jugadorRequest): Response<jugadorDto>

    @PUT("api/Jugadores/{id}")
    suspend fun putJugador(@Path("id") id: Int, @Body jugador: jugadorRequest): Response<Unit>
}