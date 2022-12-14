package space.game.android.cryptoinfo.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import kotlinx.coroutines.delay
import space.game.android.cryptoinfo.data.database.CoinInfoDatabase
import space.game.android.cryptoinfo.data.mapper.CoinMapper
import space.game.android.cryptoinfo.data.network.ApiFactory
import space.game.android.cryptoinfo.domain.CoinInfo
import space.game.android.cryptoinfo.domain.CoinRepository

class CoinRepositoryImpl(private val application: Application) : CoinRepository {

    private val coinInfoDao = CoinInfoDatabase.getInstance(application).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService
    private val mapper = CoinMapper()


    override fun getListCoinInfo(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getListCoinInfo()) { list ->
            list.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }


    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getCoinInfo(fromSymbol)) {
            mapper.mapDbModelToEntity(it)
        }
    }


    override suspend fun loadData() {
        while (true) {
            try {
                val topCoins = apiService.getTopCoins(limit = 20)
                val fromSymbol = mapper.mapNamesListToString(topCoins)
                val json = apiService.getFullCoinInfo(fSyms = fromSymbol)
                val listCoinInfoDto = mapper.mapJsonToListCoinInfo(json)
                val dbModelList = listCoinInfoDto.map { mapper.mapDtoToDbModel(it) }
                coinInfoDao.insertCoinInfo(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }
    }
}