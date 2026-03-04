package edu.ucne.angel_almonte_ap2_p2.data.remote.remoteDatasource

import android.net.http.HttpException
import edu.ucne.angel_almonte_ap2_p2.data.remote.ExamenApi
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.ExamenDto
import javax.inject.Inject

class ExamenRemoteDataSource @Inject constructor(
    private val api: ExamenApi
) {

    suspend fun getExamen(
        page: Int,
        limit: Int,
        name: String?,
        gender: String?,
        race: String?,
    ): Result<ExamenDto> {
        try {
            val response = api.getExamen(page, limit, name, gender, race)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }

    suspend fun getCharacterDetail(id: Int): Result<ExamenDto> {
        try {
            val response = api.getExamen(id)
            if (!response.isSuccessful) {
                return Result.failure(Exception("Error de red ${response.code()}"))
            }
            return Result.success(response.body()!!)
        } catch (e: HttpException) {
            return Result.failure(Exception("Error de servidor", e))
        } catch (e: Exception) {
            return Result.failure(Exception("Error desconocido", e))
        }
    }
}