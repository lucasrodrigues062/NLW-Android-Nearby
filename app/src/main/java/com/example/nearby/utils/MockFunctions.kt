package com.example.nearby.utils

import com.example.nearby.data.model.Market
import com.example.nearby.data.model.Rule
import com.google.android.gms.maps.model.LatLng

fun createMockMarket(id: String = "1"): Market {
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
        cover = "https://example.com/images/market-cover.jpg",
        //rules = createRules(id)
        )
}

fun createRules(marketId: String): List<Rule> {
    return listOf(
        Rule(
            "Regra 1", "Descrição da regra 1",
            marketId = marketId
        ),
        Rule(
            "Regra 2", "Descrição da regra 2",
            marketId = marketId
        ),
        Rule(
            "Regra 3", "Descrição da regra 3",
            marketId = marketId
        )
    )

}

val mockUserLocation = LatLng(
    -23.561187293883442,
    -46.656451388116494
)