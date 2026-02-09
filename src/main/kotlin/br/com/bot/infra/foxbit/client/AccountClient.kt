package br.com.bot.infra.foxbit.client

import br.com.bot.infra.foxbit.dto.response.AccountsResponse
import br.com.bot.infra.foxbit.dto.response.MemberResponse
import br.com.bot.infra.http.HttpClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AccountClient(
    private val http: HttpClient
) {

    fun getMe(): Mono<MemberResponse> =
        http.get(
            "/rest/v3/me",
            MemberResponse::class.java
        )

    fun getAccounts(): Mono<AccountsResponse> =
        http.get(
            "/rest/v3/accounts",
            AccountsResponse::class.java
        )

    fun getTransactions(currency: String): Mono<String> =
        http.get(
            "/rest/v3/accounts/$currency/transactions",
            String::class.java
        )

    fun getDeposits(): Mono<String> =
        http.get(
            "/rest/v3/deposits",
            String::class.java
        )

    fun getWithdrawals(): Mono<String> =
        http.get(
            "/rest/v3/withdrawals",
            String::class.java
        )
}
