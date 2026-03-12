package edu.ucne.angel_almonte_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object JugadorList : Screen()

    @Serializable
    data class JugadorDetail(val id: Int) : Screen()
}