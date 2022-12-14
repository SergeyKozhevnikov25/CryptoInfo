package space.game.android.cryptoinfo.domain

class GetListCoinInfoUseCase(private val repository: CoinRepository) {

    operator fun invoke() = repository.getListCoinInfo()
}
