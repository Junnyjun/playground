package git.io.statemachine.state

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StateSelectorTest {
    @Autowired
    lateinit var stateSelector: StateSelector

    @Test
    fun testStateSelector() {
        stateSelector.invoke()
    }
}