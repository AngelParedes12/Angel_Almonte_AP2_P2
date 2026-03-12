package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p2.data.remote.Resource
import edu.ucne.angel_almonte_ap2_p2.data.remote.dto.jugadorRequest
import edu.ucne.angel_almonte_ap2_p2.domain.useCase.GetJugadorDetailUseCase
import edu.ucne.angel_almonte_ap2_p2.domain.useCase.SaveJugadorUseCase
import edu.ucne.angel_almonte_ap2_p2.domain.useCase.UpdateJugadorUseCase
import edu.ucne.angel_almonte_ap2_p2.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailJugadorViewModel @Inject constructor(
    private val getJugadorDetailUseCase: GetJugadorDetailUseCase,
    private val saveJugadorUseCase: SaveJugadorUseCase,
    private val updateJugadorUseCase: UpdateJugadorUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(DetailJugadorUiState())
    val state = _state.asStateFlow()

    init {
        val args = savedStateHandle.toRoute<Screen.JugadorDetail>()
        if (args.id > 0) {
            loadJugador(args.id)
        }
    }

    fun onEvent(event: DetailJugadorEvent) {
        when (event) {
            is DetailJugadorEvent.OnNombresChange -> _state.update {
                it.copy(nombres = event.nombres, nombresError = null)
            }
            is DetailJugadorEvent.OnEmailChange -> _state.update {
                it.copy(email = event.email, emailError = null)
            }
            DetailJugadorEvent.SaveJugador -> validateAndSave()
        }
    }

    private fun loadJugador(id: Int) {
        viewModelScope.launch {
            getJugadorDetailUseCase(id).collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> {
                        val jugador = result.data
                        if (jugador != null) {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    jugadorId = jugador.jugadorId,
                                    nombres = jugador.nombres,
                                    email = jugador.email
                                )
                            }
                        }
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }

    private fun validateAndSave() {
        val currentState = _state.value

        val isNombresValid = currentState.nombres.isNotBlank()
        val isEmailValid = currentState.email.isNotBlank() && currentState.email.contains("@")

        if (!isNombresValid || !isEmailValid) {
            _state.update {
                it.copy(
                    nombresError = if (!isNombresValid) "El nombre no puede estar vacío" else null,
                    emailError = if (!isEmailValid) "Email inválido o vacío" else null
                )
            }
            return
        }

        saveOrUpdateJugador()
    }

    private fun saveOrUpdateJugador() {
        viewModelScope.launch {
            val currentState = _state.value

            val request = jugadorRequest(
                nombres = currentState.nombres,
                email = currentState.email
            )

            val flowResult = if (currentState.jugadorId > 0) {
                updateJugadorUseCase(currentState.jugadorId, request)
            } else {
                saveJugadorUseCase(request)
            }

            flowResult.collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(isLoading = false, success = true) }
                    is Resource.Error -> _state.update { it.copy(isLoading = false, error = result.message) }
                }
            }
        }
    }
}