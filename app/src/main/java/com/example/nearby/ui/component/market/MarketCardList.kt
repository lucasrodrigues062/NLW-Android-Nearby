package com.example.nearby.ui.component.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nearby.data.model.Market
import com.example.nearby.ui.theme.Typography

@Composable
fun MarketCardList(
    modifier: Modifier = Modifier,
    markets: List<Market>,
    onMarketClick: (Market) -> Unit
) {

    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        item {
            Text(text = "Explore locais perto de você", style = Typography.bodyLarge)
        }
        items(
            items = markets,
            key = { it.id }) { market ->
            MarketCard(
                market = market,
                onClick = { onMarketClick(market) }
            )
        }
    }

}

