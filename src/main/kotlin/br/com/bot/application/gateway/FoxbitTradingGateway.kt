package br.com.bot.application.gateway

import br.com.bot.domain.market.Currency
import br.com.bot.domain.market.MarketSymbol
import br.com.bot.domain.model.Balance
import br.com.bot.domain.model.Ticker
import br.com.bot.infra.foxbit.client.AccountClient
import br.com.bot.infra.foxbit.client.MarketDataClient
import br.com.bot.infra.foxbit.mapper.toDomain
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class FoxbitTradingGateway(
    private val accountClient: AccountClient,
    private val marketClient: MarketDataClient
) {

    fun getBalance(currency: Currency): Mono<Balance> =
        accountClient.getAccounts()
            .map { it.toDomain() }
            .map { balances ->
                balances[currency]
                    ?: Balance.zero(currency)
            }

    fun getTicker(symbol: MarketSymbol): Mono<Ticker> =
        marketClient.getTicker(symbol)
            .map { it.toDomain(symbol) }
}
