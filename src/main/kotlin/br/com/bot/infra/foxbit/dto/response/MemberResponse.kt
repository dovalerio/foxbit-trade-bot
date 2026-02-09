package br.com.bot.infra.foxbit.dto.response

data class MemberResponse(
    val sn: String,
    val email: String,
    val level: Int,
    val disabled: Boolean,
    val created_at: String
)