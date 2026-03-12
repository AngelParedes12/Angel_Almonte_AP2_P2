package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list

sealed interface ListJugadorEvent {
    data object LoadJugadores : ListJugadorEvent
}