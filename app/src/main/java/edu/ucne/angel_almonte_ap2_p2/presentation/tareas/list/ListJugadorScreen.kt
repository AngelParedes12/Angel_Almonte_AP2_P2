package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.angel_almonte_ap2_p2.domain.model.jugador

@Composable
fun ListJugadorScreen(
    viewModel: ListJugadorViewModel = hiltViewModel(),
    onJugadorClick: (Int) -> Unit,
    onCreateJugador: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ListJugadorBodyScreen(
        state = state,
        onEvent = viewModel::onEvent,
        onJugadorClick = onJugadorClick,
        onCreateJugador = onCreateJugador
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListJugadorBodyScreen(
    state: ListJugadorUiState,
    onEvent: (ListJugadorEvent) -> Unit,
    onJugadorClick: (Int) -> Unit,
    onCreateJugador: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Jugadores Snake") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateJugador) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            state.error?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.jugadores) { jugador ->
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clickable { onJugadorClick(jugador.jugadorId) }
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "ID: ${jugador.jugadorId}",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = "Nombre: ${jugador.nombres}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "Email: ${jugador.email}",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Total de jugadores: ${state.jugadores.size}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListJugadorBodyScreenPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ListJugadorBodyScreen(
                state = ListJugadorUiState(
                    isLoading = false,
                    jugadores = listOf(
                        jugador(jugadorId = 1, nombres = "Enel Almonte", email = "EnelAlmonte@gmail.com"),
                        jugador(jugadorId = 2, nombres = "Estrada Erick", email = "Estrada@hotmail.com")
                    )
                ),
                onEvent = {},
                onJugadorClick = {},
                onCreateJugador = {}
            )
        }
    }
}