package br.com.bot.infra.foxbit.config


import br.com.bot.infra.config.FoxbitProperties
import br.com.bot.infra.foxbit.auth.FoxbitSigner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class FoxbitWebClientConfig {


    @Bean
    fun foxbitWebClient(
        properties: FoxbitProperties,
        signer: FoxbitSigner
    ): WebClient {
        return WebClient.builder()
            .baseUrl(properties.baseUrl) // https://api.foxbit.com.br
            .filter { request, next ->

                val timestamp = System.currentTimeMillis().toString()
                val method = request.method().name()
                val path = request.url().path
                val body = ""

                val preHash = timestamp + method + path + body
                val signature = signer.sign(preHash)
                val newRequest = ClientRequest.from(request)
                    .header("X-FB-ACCESS-KEY", properties.apiKey.trim())
                    .header("X-FB-ACCESS-SIGNATURE", signature)
                    .header("X-FB-ACCESS-TIMESTAMP", timestamp)
                    .build()

                next.exchange(newRequest)
            }
            .build()
    }
}