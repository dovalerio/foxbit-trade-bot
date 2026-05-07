package Br.com.bot

import br.com.bot.FoxbitTradeBotApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

// Test package is 'Br.com.bot' (capital B) while the main application is in
// 'br.com.bot' (lowercase). On Linux (case-sensitive filesystem) these are
// different packages, so @SpringBootTest cannot auto-detect @SpringBootApplication
// by walking up the package hierarchy. Specifying classes= makes discovery explicit.
@SpringBootTest(
    classes = [FoxbitTradeBotApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.NONE
)
@TestPropertySource(properties = [
    "FOXBIT_API_KEY=test-key",
    "FOXBIT_API_SECRET=test-secret"
])
class FoxbitTradeBotApplicationTests {

	@Test
	fun contextLoads() {
	}

}
