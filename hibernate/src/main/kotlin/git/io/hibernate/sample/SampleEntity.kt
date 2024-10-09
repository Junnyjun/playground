package git.io.hibernate.sample

import jakarta.persistence.*

@Entity
@Table(name = "sample_entity")
open class SampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null
    var name: String? = null
    var description: String? = null
}