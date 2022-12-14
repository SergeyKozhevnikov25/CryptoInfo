package space.game.android.cryptoinfo.data.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameInfoDto(

    @SerializedName("CoinInfo")
    @Expose
    val coinNameDto: CoinNameDto? = null
)