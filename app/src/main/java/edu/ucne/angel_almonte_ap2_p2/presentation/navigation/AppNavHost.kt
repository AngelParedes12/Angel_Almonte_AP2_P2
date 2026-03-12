package edu.ucne.angel_almonte_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.angel_almonte_ap2_p2.presentation.tareas.details.DetailJugadorScreen
import edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list.ListJugadorScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.JugadorList
    ) {
        composable<Screen.JugadorList> {
            ListJugadorScreen(
                onJugadorClick = { id ->
                    navController.navigate(Screen.JugadorDetail(id = id))
                },
                onCreateJugador = {
                    navController.navigate(Screen.JugadorDetail(id = 0))
                }
            )
        }

        composable<Screen.JugadorDetail> {
            DetailJugadorScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}