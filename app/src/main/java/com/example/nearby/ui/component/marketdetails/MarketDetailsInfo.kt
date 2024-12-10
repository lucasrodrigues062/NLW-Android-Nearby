package com.example.nearby.ui.component.marketdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nearby.R
import com.example.nearby.data.model.Market
import com.example.nearby.ui.theme.Gray400
import com.example.nearby.ui.theme.Gray500
import com.example.nearby.ui.theme.Typography


@Composable
fun MarketDetailsInfo(modifier: Modifier = Modifier, market: Market) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(text = "Informações", style = Typography.headlineSmall, color = Gray400)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_ticket),
                    tint = Gray500,
                    contentDescription = "Ícone cupons"
                )
                Text(
                    text = "${market.coupons} ${if (market.coupons == 1) "cupom disponível" else "cupons disponíveis"}",
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_map_pin),
                    tint = Gray500,
                    contentDescription = "Ícone Endereço"
                )
                Text(
                    text = market.address,
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_phone),
                    tint = Gray500,
                    contentDescription = "Ícone Telefone"
                )
                Text(
                    text = market.phone,
                    style = Typography.labelMedium,
                    color = Gray500
                )
            }
        }
    }
}

@Preview
@Composable
private fun MarketDetailsPreview() {
    MarketDetailsInfo(Modifier.fillMaxWidth(), createMockMarket())
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