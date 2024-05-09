package git.io.statemachine.state

import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.statemachine.StateMachine
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

fun interface StateSelector {
    fun invoke()


    @Service
    class StateSelectorUsecase(
        private val stateMachine: StateMachine<String,String>
    ):  StateSelector{
        override fun invoke() {
            stateMachine.start()
            stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("E1").build())).subscribe()
            stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("E2").build())).subscribe()
            stateMachine.sendEvent(Mono.just(MessageBuilder.withPayload("EE").build())).subscribe()
        }

    }
}