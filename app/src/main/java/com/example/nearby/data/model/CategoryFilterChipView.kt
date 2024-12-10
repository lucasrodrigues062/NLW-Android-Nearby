package com.example.nearby.data.model

import androidx.annotation.DrawableRes
import com.example.nearby.R

enum class CategoryFilterChipView (val description: String, @DrawableRes val icon: Int) {

    ALIMENTACAO("Alimentação", R.drawable.ic_tools_kitchen_2),
    COMPRAS("Compras", R.drawable.ic_shopping_bag),
    HOSPEDAGEM("Hospedagem", R.drawable.ic_bed),
    SUPERMERCADO("Supermercado", R.drawable.ic_shopping_cart),
    ENTRETENIMENTO("Cinema", R.drawable.ic_movie),
    FARMACIA("Farmácia", R.drawable.ic_first_aid_kit),
    COMBUSTIVEL("Combustível", R.drawable.ic_gas_station),
    PADARIA("Padaria", R.drawable.ic_bakery);

    companion object {
        fun fromDescription(description: String): CategoryFilterChipView? {
            return entries.find { it.description == description }
        }
    }
}
