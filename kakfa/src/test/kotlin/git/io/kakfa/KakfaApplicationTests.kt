package git.io.kakfa

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.FileInputStream
import java.io.InputStream
import java.util.concurrent.atomic.AtomicLong

@SpringBootTest
class KakfaApplicationTests {

	@Test
	fun contextLoads() {
		val stream = FileInputStream("src/main/resources/application.properties")
		AtomicLong()
	}

}
