package space.game.android.cryptoinfo.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CoinInfoDao {

    @Query("SELECT * FROM full_price_list ORDER BY mktCap  DESC")
    fun getListCoinInfo(): LiveData<List<CoinInfoDbModel>>


    @Query("SELECT * FROM full_price_list WHERE fromSymbol == :fSym LIMIT 1")
    fun getCoinInfo(fSym: String): LiveData<CoinInfoDbModel>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinInfo(coinInfo: List<CoinInfoDbModel>)
}