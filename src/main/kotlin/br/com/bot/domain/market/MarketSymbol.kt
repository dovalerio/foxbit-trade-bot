package br.com.bot.domain.market

enum class MarketSymbol(
    val base: Currency,
    val quote: Currency
) {

    BTC_BRL(Currency.BTC, Currency.BRL),
    ETH_BRL(Currency.ETH, Currency.BRL),
    LTC_BRL(Currency.LTC, Currency.BRL),
    XRP_BRL(Currency.XRP, Currency.BRL),
    ADA_BRL(Currency.ADA, Currency.BRL),
    SOL_BRL(Currency.SOL, Currency.BRL),
    DOGE_BRL(Currency.DOGE, Currency.BRL);

    fun toApiSymbol(): String =
        "${base.apiValue}${quote.apiValue}"

    override fun toString(): String = toApiSymbol()
}
