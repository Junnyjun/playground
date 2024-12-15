package git.io.kakfa.message.`in`

import git.io.kakfa.message.out.SampleGateway
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

interface SampleController {
    fun sample()


    @Controller
    class SampleMessageController(){

        @KafkaListener(topics = ["all-copy.employees.salaries"], concurrency = "3", groupId = "sample")
        fun sampleMessage(message: String){
            println("Received message: $message")
        }
    }

    @RestController
    @RequestMapping("/sample")
    class SampleRestHttpController():SampleController{
        override fun sample() {
            TODO("Not yet implemented")
        }

    }
}