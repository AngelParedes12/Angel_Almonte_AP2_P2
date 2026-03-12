package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.details

data class DetailJugadorUiState(
    val isLoading: Boolean = false,
    val jugadorId: Int = 0,
    val nombres: String = "",
    val nombresError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val error: String? = null,
    val success: Boolean = false
)