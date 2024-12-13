package com.example.nearby.ui.screen.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nearby.core.network.RemoteDataSource
import com.example.nearby.data.model.Rule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarketDetailsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MarketDetailsUiState())
    val uiState: StateFlow<MarketDetailsUiState> = _uiState.asStateFlow()

    fun onEvent(event: MarketDetailsUiEvent) {
        when (event) {
            is MarketDetailsUiEvent.OnFetchRules -> fetchRules(event.marketId)
            is MarketDetailsUiEvent.OnFetchCoupon -> fetchCoupon(event.qrCodeContent)
            MarketDetailsUiEvent.OnResetCoupon -> resetCoupon()
        }
    }

    private fun fetchRules(marketId: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                RemoteDataSource.getMarketDetails(marketId).fold(
                    onSuccess = { marketDetails ->
                        currentState.copy(rules = marketDetails.rules)
                    },
                    onFailure = { _ ->
                        currentState.copy(
                            rules = emptyList()
                        )
                    }
                )
            }

        }
    }

    private fun resetCoupon() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(coupon = null)
            }

        }
    }

    private fun fetchCoupon(qrCodeContent: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                RemoteDataSource.patchCoupon(qrCodeContent).fold(
                    onSuccess = { coupon ->
                        currentState.copy(
                            coupon = coupon.coupon
                        )
                    },
                    onFailure = { _ ->
                        currentState.copy(
                            coupon = "null"
                        )
                    }
                )
            }

        }
    }
}

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null,
)

sealed class MarketDetailsUiEvent {
    data object OnResetCoupon : MarketDetailsUiEvent()
    data class OnFetchRules(val marketId: String) : MarketDetailsUiEvent()
    data class OnFetchCoupon(val qrCodeContent: String) : MarketDetailsUiEvent()

}
