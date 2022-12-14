package space.game.android.cryptoinfo.domain

data class CoinInfo(
    val fromSymbol: String,
    val type: String?,
    val market: String?,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String,
    val lastVolume: String?,
    val lastVolumeTo: String?,
    val lastTradeId: String?,
    val volumeDay: String?,
    val volumeDayTo: String?,
    val volume24Hour: String?,
    val volume24HourTo: String?,
    val openDay: String?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val volumeHour: String?,
    val volumeHourTo: String?,
    val openHour: String?,
    val highHour: String?,
    val lowHour: String?,
    val mktCap: Double?,
    val change_24: String?,
    val imageUrl: String
)