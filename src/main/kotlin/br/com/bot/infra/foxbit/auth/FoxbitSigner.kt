package br.com.bot.infra.foxbit.auth


import br.com.bot.infra.config.FoxbitProperties
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Component
class FoxbitSigner(
    properties: FoxbitProperties
) {

    private val secretKey = SecretKeySpec(
        properties.apiSecret.toByteArray(StandardCharsets.UTF_8),
        "HmacSHA256"
    )

    fun sign(payload: String): String {
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(secretKey)

        val hash = mac.doFinal(payload.toByteArray(StandardCharsets.UTF_8))

        return hash.joinToString("") { "%02x".format(it) }
    }
}
