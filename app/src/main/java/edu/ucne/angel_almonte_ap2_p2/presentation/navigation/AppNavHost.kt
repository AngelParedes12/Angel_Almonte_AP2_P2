package edu.ucne.angel_almonte_ap2_p2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.angel_almonte_ap2_p2.presentation.tareas.details.DetailExamenScreen
import edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list.ListExamenScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ExamenList
    ) {
        composable<Screen.ExamenList> {
            ListExamenScreen(
                onPlanetClick = { examenId ->
                    navController.navigate(Screen.ExamenDetail(examenId))
                }
            )
        }

        composable<Screen.ExamenDetail> {
            DetailExamenScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}