package git.io.hibernate.sample

import org.springframework.data.jpa.repository.JpaRepository

interface SampleRepository: JpaRepository<SampleEntity, Long> {

}