package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.details

sealed interface DetailJugadorEvent {
    data class OnNombresChange(val nombres: String) : DetailJugadorEvent
    data class OnEmailChange(val email: String) : DetailJugadorEvent
    data object SaveJugador : DetailJugadorEvent
}