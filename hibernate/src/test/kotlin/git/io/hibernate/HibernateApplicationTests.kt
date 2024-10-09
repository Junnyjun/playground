package git.io.hibernate

import git.io.hibernate.sample.SampleEntity
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HibernateApplicationTests {
    @Autowired
    lateinit var entityManagerFactory: EntityManagerFactory
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
        entityManager.find(SampleEntity::class.java, 1L)
        println(entityManagerFactory)
    }

}
