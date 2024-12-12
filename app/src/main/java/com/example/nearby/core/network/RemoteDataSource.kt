package com.example.nearby.core.network

import com.example.nearby.core.network.KtorHttpClient.httpClientAndroid
import com.example.nearby.data.model.Category
import com.example.nearby.data.model.Coupon
import com.example.nearby.data.model.Market
import com.example.nearby.data.model.MarketDetails
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch

object RemoteDataSource {
    private const val LOCAL_HOST_EMULATOR_BASE_URL = "http://10.0.0.2:3333/"

    private const val BASE_URL = LOCAL_HOST_EMULATOR_BASE_URL

    suspend fun getCategories(): Result<List<Category>>  = kotlin.runCatching {
        val categories: List<Category> = httpClientAndroid.get("$BASE_URL/categories").body()
        return@runCatching categories
    }

    suspend fun getMarkets(categoryId: String): Result<List<Market>>  = kotlin.runCatching {
        val markets: List<Market> = httpClientAndroid.get("$BASE_URL/markets/category/${categoryId}").body()
        return@runCatching markets
    }

    suspend fun getMarketDetails(marketId: String): Result<MarketDetails>  = kotlin.runCatching {
        val market: MarketDetails = httpClientAndroid.get("$BASE_URL/markets/${marketId}").body()
        return@runCatching market
    }

    suspend fun patchCoupon(marketId: String): Result<Coupon>  = kotlin.runCatching {
        val coupon: Coupon = httpClientAndroid.patch("$BASE_URL/coupons/${marketId}").body()
        return@runCatching coupon
    }

}