package Br.com.bot

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestPropertySource(properties = [
    "FOXBIT_API_KEY=test-key",
    "FOXBIT_API_SECRET=test-secret"
])
class FoxbitTradeBotApplicationTests {

	@Test
	fun contextLoads() {
	}

}
