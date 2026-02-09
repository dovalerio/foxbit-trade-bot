package br.com.bot.infra.http

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class HttpClient(
    val webClient: WebClient
) {

    fun <Res : Any> get(
        path: String,
        responseType: Class<Res>,
        query: Map<String, Any?> = emptyMap(),
        headers: HttpHeaders = HttpHeaders.EMPTY
    ): Mono<Res> =
        request(HttpMethod.GET, path, responseType, query, headers, null)

    fun <Req : Any, Res : Any> post(
        path: String,
        body: Req,
        responseType: Class<Res>,
        query: Map<String, Any?> = emptyMap(),
        headers: HttpHeaders = HttpHeaders.EMPTY
    ): Mono<Res> =
        request(HttpMethod.POST, path, responseType, query, headers, body)

    fun <Req : Any, Res : Any> put(
        path: String,
        body: Req,
        responseType: Class<Res>,
        query: Map<String, Any?> = emptyMap(),
        headers: HttpHeaders = HttpHeaders.EMPTY
    ): Mono<Res> =
        request(HttpMethod.PUT, path, responseType, query, headers, body)

    fun delete(
        path: String,
        headers: HttpHeaders = HttpHeaders.EMPTY
    ): Mono<Void> =
        webClient.delete()
            .uri(path)
            .headers { it.addAll(headers) }
            .retrieve()
            .toBodilessEntity()
            .then()

    fun <Req : Any, Res : Any> request(
        method: HttpMethod,
        path: String,
        responseType: Class<Res>,
        query: Map<String, Any?>,
        headers: HttpHeaders,
        body: Req?
    ): Mono<Res> {

        val spec = webClient
            .method(method)
            .uri { builder ->
                builder.path(path)
                query.forEach { (k, v) ->
                    v?.let { builder.queryParam(k, it) }
                }
                builder.build()
            }
            .headers { it.addAll(headers) }

        val response = if (body != null) spec.bodyValue(body) else spec

        return response
            .retrieve()
            .bodyToMono(responseType)
    }
}
