package com.example.nearby.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearby.data.model.Category
import com.example.nearby.data.model.Market
import com.example.nearby.ui.component.category.NearbyCategoryFilterChipList
import com.example.nearby.ui.component.market.MarketCardList
import com.example.nearby.utils.createMockMarket
import com.example.nearby.ui.theme.Gray100
import com.google.maps.android.compose.GoogleMap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
    onNavigateToMarketDetails: (Market) -> Unit
) {

    val bottomSheetState = rememberBottomSheetScaffoldState()
    var isBottomSheetOpened by remember { mutableStateOf(true) }
    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = true) {
        onEvent(HomeUiEvent.OnFetchCategories)
    }

    if (isBottomSheetOpened) {
        BottomSheetScaffold(
            modifier = modifier,
            scaffoldState = bottomSheetState,
            sheetContainerColor = Gray100,
            sheetPeekHeight = configuration.screenHeightDp.dp * 0.5f,
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetContent = {
                if (!uiState.markets.isNullOrEmpty()) {
                    MarketCardList(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                        markets = uiState.markets,
                        onMarketClick = { selectedMarket -> onNavigateToMarketDetails(selectedMarket) })
                }

            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    GoogleMap(modifier = Modifier.fillMaxSize())
                    if (!uiState.categories.isNullOrEmpty()) {
                        NearbyCategoryFilterChipList(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp)
                                .align(Alignment.TopStart),
                            categories = uiState.categories,
                            onSelectedCategoryChange = { selectedCategory ->
                                onEvent(
                                    HomeUiEvent.OnFetchMarkets(selectedCategory.id)
                                )
                            }
                        )
                    }

                }

            },
        )
    }

}