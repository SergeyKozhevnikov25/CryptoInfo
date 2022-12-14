package space.game.android.cryptoinfo.data.mapper

import com.google.gson.Gson
import space.game.android.cryptoinfo.data.database.CoinInfoDbModel
import space.game.android.cryptoinfo.data.network.model.CoinInfoDto
import space.game.android.cryptoinfo.data.network.model.CoinInfoJsonDto
import space.game.android.cryptoinfo.data.network.model.CoinNamesListDto
import space.game.android.cryptoinfo.domain.CoinInfo
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

    fun mapDtoToDbModel(dto: CoinInfoDto): CoinInfoDbModel {
        return CoinInfoDbModel(
            fromSymbol = dto.fromSymbol,
            type = dto.type,
            market = dto.market,
            toSymbol = dto.toSymbol,
            price = dto.price,
            lastUpdate = dto.lastUpdate,
            lastVolume = dto.lastVolume,
            lastVolumeTo = dto.lastVolumeTo,
            lastTradeId = dto.lastTradeId,
            volumeDay = dto.volumeDay,
            volumeDayTo = dto.volumeDayTo,
            volume24Hour = dto.volume24Hour,
            volume24HourTo = dto.volume24HourTo,
            openDay = dto.openDay,
            highDay = dto.highDay,
            lowDay = dto.lowDay,
            lastMarket = dto.lastMarket,
            volumeHour = dto.volumeHour,
            volumeHourTo = dto.volumeHourTo,
            openHour = dto.openHour,
            highHour = dto.highHour,
            lowHour = dto.lowHour,
            mktCap = dto.mktCap,
            change_24 = dto.change_24,
            imageUrl = BASE_IMAGE_URL + dto.imageUrl
        )
    }

    fun mapDbModelToEntity(dbModel: CoinInfoDbModel): CoinInfo {
        return CoinInfo(
            fromSymbol = dbModel.fromSymbol,
            type = dbModel.type,
            market = dbModel.market,
            toSymbol = dbModel.toSymbol,
            price = dbModel.price,
            lastUpdate = convertTimestampToTime(dbModel.lastUpdate),
            lastVolume = dbModel.lastVolume,
            lastVolumeTo = dbModel.lastVolumeTo,
            lastTradeId = dbModel.lastTradeId,
            volumeDay = dbModel.volumeDay,
            volumeDayTo = dbModel.volumeDayTo,
            volume24Hour = dbModel.volume24Hour,
            volume24HourTo = dbModel.volume24HourTo,
            openDay = dbModel.openDay,
            highDay = dbModel.highDay,
            lowDay = dbModel.lowDay,
            lastMarket = dbModel.lastMarket,
            volumeHour = dbModel.volumeHour,
            volumeHourTo = dbModel.volumeHourTo,
            openHour = dbModel.openHour,
            highHour = dbModel.highHour,
            lowHour = dbModel.lowHour,
            mktCap = dbModel.mktCap,
            change_24 = dbModel.change_24,
            imageUrl = dbModel.imageUrl
        )
    }

    fun mapJsonToListCoinInfo(jsonContainer: CoinInfoJsonDto): List<CoinInfoDto> {
        val result = mutableListOf<CoinInfoDto>()
        val jsonObject = jsonContainer.json ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo =
                    Gson().fromJson(
                        currencyJson.getAsJsonObject(currencyKey),
                        CoinInfoDto::class.java
                    )
                result.add(priceInfo)
            }
        }
        return result
    }


    fun mapNamesListToString(namesListDto: CoinNamesListDto): String {
        return namesListDto.names?.map {
            it.coinNameDto?.name
        }?.joinToString(",") ?: ""
    }


    private fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    companion object {
        const val BASE_IMAGE_URL = "https://cryptocompare.com"
    }
}