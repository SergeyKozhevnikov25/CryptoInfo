package space.game.android.cryptoinfo.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import space.game.android.cryptoinfo.data.network.model.CoinInfoJsonDto
import space.game.android.cryptoinfo.data.network.model.CoinNamesListDto

interface ApiService {

    @GET("top/totalvolfull")
    suspend fun getTopCoins(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY
    ): CoinNamesListDto


    @GET("pricemultifull")
    suspend fun getFullCoinInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinInfoJsonDto


    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val CURRENCY = "USD"
    }
}