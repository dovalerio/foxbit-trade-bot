package br.com.bot.infra.foxbit.client

import br.com.bot.domain.market.MarketSymbol
import br.com.bot.infra.foxbit.dto.response.Ticker24hResponse
import br.com.bot.infra.http.HttpClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class MarketDataClient(
    private val http: HttpClient
) {

    fun getTicker(symbol: MarketSymbol): Mono<Ticker24hResponse> =
        http.get(
            "/rest/v3/markets/${symbol.toApiSymbol()}/ticker/24hr",
            Ticker24hResponse::class.java
        )

    fun getAllTickers(): Mono<String> =
        http.get(
            "/rest/v3/markets/ticker/24hr",
            String::class.java
        )

    fun getOrderBook(symbol: MarketSymbol): Mono<String> =
        http.get(
            "/rest/v3/markets/${symbol.toApiSymbol()}/orderbook",
            String::class.java
        )

    fun getTrades(symbol: MarketSymbol): Mono<String> =
        http.get(
            "/rest/v3/markets/${symbol.toApiSymbol()}/trades/history",
            String::class.java
        )

    fun getCandles(symbol: MarketSymbol): Mono<String> =
        http.get(
            "/rest/v3/markets/${symbol.toApiSymbol()}/candlesticks",
            String::class.java
        )
}
