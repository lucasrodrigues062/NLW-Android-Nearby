package com.example.nearby.ui.component.marketdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearby.R
import com.example.nearby.data.model.Market
import com.example.nearby.data.model.Rule
import com.example.nearby.ui.theme.Gray400
import com.example.nearby.ui.theme.Gray500
import com.example.nearby.ui.theme.Typography


@Composable
fun MarketDetailsRules(modifier: Modifier = Modifier, market: Market, rules: List<Rule>) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Regulamento", style = Typography.headlineSmall, color = Gray400)

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = rules.joinToString("\n", transform = { "• " + it.description }),
            style = Typography.labelMedium,
            lineHeight = 24.sp,
            color = Gray500
        )



    }
}

@Preview
@Composable
private fun MarketDetailsPreview() {
    MarketDetailsRules(Modifier.fillMaxWidth(), market = createMockMarket(), createMockRules())
}

private fun createMockRules(): List<Rule> {
    return listOf(
        Rule(
            "Regra 1", "Descrição da regra 1",
            marketId = "1"
        ),
        Rule(
            "Regra 2", "Descrição da regra 2",
            marketId = "2"
        ),
        Rule(
            "Regra 3", "Descrição da regra 3",
            marketId = "3"
        )
    )
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

