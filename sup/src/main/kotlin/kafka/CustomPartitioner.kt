package io.junnyland.kafka

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster

class CustomPartitioner: Partitioner {
	override fun partition(
		p0: String?,
		p1: Any?,
		p2: ByteArray?,
		p3: Any?,
		p4: ByteArray?,
		p5: Cluster?
	): Int {
		TODO("Not yet implemented")
	}

	override fun close() {
		TODO("Not yet implemented")
	}

	override fun configure(p0: Map<String?, *>?) {
		TODO("Not yet implemented")
	}
}