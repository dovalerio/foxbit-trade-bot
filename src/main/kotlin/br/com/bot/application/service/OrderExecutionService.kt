package br.com.bot.application.service

import br.com.bot.domain.order.OrderIntent
import br.com.bot.infra.foxbit.client.TradingClient
import br.com.bot.infra.foxbit.dto.request.CreateOrderRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OrderExecutionService(
    private val tradingClient: TradingClient
) {

    private val log = LoggerFactory.getLogger(javaClass)

    fun execute(intent: OrderIntent): Mono<String> {

        val request = CreateOrderRequest(
            market_symbol = intent.market.toApiSymbol(),
            side = intent.side.name.lowercase(),
            type = intent.type.name.lowercase(),
            quantity = intent.quantity,
            price = intent.price,
            client_order_id = null
        )

        log.info("Sending order: {}", request)

        return tradingClient.createOrder(request)
            .doOnNext { log.info("Order response: {}", it) }
    }
}
