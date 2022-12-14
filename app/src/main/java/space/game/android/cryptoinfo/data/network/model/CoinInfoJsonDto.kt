package space.game.android.cryptoinfo.data.network.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoJsonDto(

    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null

)