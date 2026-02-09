package br.com.bot.infra.foxbit.client

import br.com.bot.infra.foxbit.dto.request.CancelOrdersRequest
import br.com.bot.infra.foxbit.dto.request.CreateOrderRequest
import br.com.bot.infra.http.HttpClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TradingClient(
    private val http: HttpClient
) {

    fun createOrder(request: CreateOrderRequest): Mono<String> =
        http.post(
            "/rest/v3/orders",
            request,
            String::class.java
        )

    fun cancelOrders(request: CancelOrdersRequest): Mono<String> =
        http.put(
            "/rest/v3/orders/cancel",
            request,
            String::class.java
        )

    fun getOrderById(orderId: String): Mono<String> =
        http.get(
            "/rest/v3/orders/by-order-id/$orderId",
            String::class.java
        )

    fun getOrderByClientId(clientId: String): Mono<String> =
        http.get(
            "/rest/v3/orders/by-client-order-id/$clientId",
            String::class.java
        )

    fun listTrades(): Mono<String> =
        http.get(
            "/rest/v3/trades",
            String::class.java
        )

    fun deleteOrder(orderId: String): Mono<Void> =
        http.delete("/rest/v3/orders/$orderId")
}
