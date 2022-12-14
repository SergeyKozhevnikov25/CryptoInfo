package space.game.android.cryptoinfo.domain

import androidx.lifecycle.LiveData

interface CoinRepository {

    fun getListCoinInfo(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo>

    suspend fun loadData()

}