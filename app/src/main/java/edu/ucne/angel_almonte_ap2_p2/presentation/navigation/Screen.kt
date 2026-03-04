package edu.ucne.angel_almonte_ap2_p2.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ExamenList : Screen()

    @Serializable
    data class ExamenDetail(val id: Int) : Screen()
}