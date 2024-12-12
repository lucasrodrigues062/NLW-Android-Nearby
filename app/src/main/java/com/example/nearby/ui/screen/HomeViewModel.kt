package com.example.nearby.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearby.core.network.RemoteDataSource
import com.example.nearby.data.model.Category
import com.example.nearby.data.model.Market
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnFetchCategories -> fetchCategories()
            is HomeUiEvent.OnFetchMarkets -> fetchMarkets(event.categoryId)
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            println("passou")
            _uiState.update { currentState ->
                RemoteDataSource.getCategories().fold(
                    onSuccess = { categories ->
                        currentState.copy(categories = categories)
                    },
                    onFailure = { _ ->
                        currentState.copy(
                            categories = emptyList()
                        )
                    }
                )
            }

        }
    }

    private fun fetchMarkets(categoryId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                RemoteDataSource.getMarkets(categoryId).fold(
                    onSuccess = { markets ->
                        currentState.copy(
                            markets = markets,
                            marketLocations = markets.map { LatLng(it.latitude, it.longitude) })
                    },
                    onFailure = { _ ->
                        currentState.copy(
                            markets = emptyList(),
                            marketLocations = emptyList()
                        )
                    }
                )
            }

        }
    }
}

data class HomeUiState(
    val categories: List<Category>? = null,
    val markets: List<Market>? = null,
    val marketLocations: List<LatLng>? = null
)

sealed class HomeUiEvent {
    data object OnFetchCategories : HomeUiEvent()
    data class OnFetchMarkets(val categoryId: String) : HomeUiEvent()

}