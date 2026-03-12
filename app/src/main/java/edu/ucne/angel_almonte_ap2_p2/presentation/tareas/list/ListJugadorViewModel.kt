package edu.ucne.angel_almonte_ap2_p2.presentation.tareas.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p2.data.remote.Resource
import edu.ucne.angel_almonte_ap2_p2.domain.useCase.GetJugadoresUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListJugadorViewModel @Inject constructor(
    private val getJugadoresUseCase: GetJugadoresUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListJugadorUiState())
    val state = _state.asStateFlow()

    init {
        loadJugadores()
    }

    fun onEvent(event: ListJugadorEvent) {
        when (event) {
            ListJugadorEvent.LoadJugadores -> loadJugadores()
        }
    }

    private fun loadJugadores() {
        viewModelScope.launch {
            getJugadoresUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update {
                        it.copy(isLoading = false, jugadores = result.data ?: emptyList())
                    }
                    is Resource.Error -> _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
            }
        }
    }
}