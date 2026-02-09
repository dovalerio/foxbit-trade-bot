package br.com.bot.domain.market

enum class Currency(
    val apiValue: String
) {
    BTC("btc"),
    ETH("eth"),
    LTC("ltc"),
    XRP("xrp"),
    USDT("usdt"),
    USDC("usdc"),
    ADA("ada"),
    SOL("sol"),
    DOGE("doge"),
    BRL("brl");

    override fun toString(): String = apiValue

    companion object {

        fun fromApi(value: String): Currency =
            entries.firstOrNull {
                it.apiValue.equals(value, ignoreCase = true)
            }
                ?: error("Unsupported currency from API: $value")
    }
}
