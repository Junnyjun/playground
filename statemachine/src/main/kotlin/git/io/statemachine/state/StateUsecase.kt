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
        private val stateMachine: StateMachine<Status, Action>
    ):  StateSelector{
        override fun invoke() {
            val message1 = Mono.just(MessageBuilder.withPayload(Action.A1).build())
            stateMachine.sendEvent(message1)
            stateMachine.sendEvent(message1)
            val message2 = Mono.just(MessageBuilder.withPayload(Action.A2).build())
            stateMachine.sendEvent(message2)
            val message3 = Mono.just(MessageBuilder.withPayload(Action.A3).build())
            stateMachine.sendEvent(message3)
        }

    }
}