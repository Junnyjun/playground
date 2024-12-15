package git.io.kakfa

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import java.io.FileInputStream
import java.io.InputStream
import java.util.concurrent.atomic.AtomicLong

@SpringBootTest
class KakfaApplicationTests {

	@Test
	fun contextLoads() {
		CoroutineScope(SupervisorJob() + Dispatchers.Default)

		val stream = FileInputStream("src/main/resources/application.properties")
		AtomicLong()
	}

}
