package br.com.bot.infra.foxbit.client

import br.com.bot.infra.http.HttpClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class SystemClient(
    private val http: HttpClient
) {

    fun getSystemTime(): Mono<String> =
        http.get(
            "/rest/v3/system/time",
            String::class.java
        )

    fun getCurrencies(): Mono<String> =
        http.get(
            "/rest/v3/currencies",
            String::class.java
        )
}
