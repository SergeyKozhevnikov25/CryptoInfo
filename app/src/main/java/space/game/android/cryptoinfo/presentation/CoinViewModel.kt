package space.game.android.cryptoinfo.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import space.game.android.cryptoinfo.data.repository.CoinRepositoryImpl
import space.game.android.cryptoinfo.domain.GetCoinInfoUseCase
import space.game.android.cryptoinfo.domain.GetListCoinInfoUseCase
import space.game.android.cryptoinfo.domain.LoadDataUseCase

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = CoinRepositoryImpl(application)

    private val getListCoinInfoUseCase = GetListCoinInfoUseCase(repository)
    private val getCoinInfoUseCase = GetCoinInfoUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val listCoinInfo = getListCoinInfoUseCase()

    fun getCoinInfo(fSym: String) = getCoinInfoUseCase(fSym)

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

}