package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list

import edu.ucne.angel_almonte_ap2_p2.domain.model.jugador

data class ListJugadorUiState(
    val isLoading: Boolean = false,
    val jugadores: List<jugador> = emptyList(),
    val error: String? = null
)