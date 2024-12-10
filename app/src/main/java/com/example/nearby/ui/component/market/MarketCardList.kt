package com.example.nearby.ui.component.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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


@Preview
@Composable
private fun MarketCardListPreview() {
    MarketCardList(
        Modifier.fillMaxWidth(),
        listOf(createMockMarket("1"), createMockMarket("2")),
        onMarketClick = {})
}


private fun createMockMarket(id: String = "1"): Market {
    return Market(
        id = id,
        categoryId = "101",
        name = "Supermercado Central",
        description = "Um supermercado completo com produtos de qualidade.",
        coupons = 1,
        latitude = -23.55052,
        longitude = -46.633308,
        address = "Rua Central, 123, São Paulo, SP",
        phone = "+55 11 98765-4321",
        cover = "https://example.com/images/market-cover.jpg"
    )
}