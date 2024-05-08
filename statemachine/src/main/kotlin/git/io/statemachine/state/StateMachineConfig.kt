package git.io.statemachine.state

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.StateContext
import org.springframework.statemachine.StateMachine
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.listener.StateMachineListenerAdapter
import org.springframework.statemachine.state.State
import java.util.*


@Configuration
@EnableStateMachine
class StateMachineConfig: EnumStateMachineConfigurerAdapter<Status, Action>(){
    private val logger = LoggerFactory.getLogger(StateMachineConfig::class.java)

    override fun configure(config: StateMachineConfigurationConfigurer<Status, Action>) {
        config.withConfiguration()
            .autoStartup(true)
            .listener(listener())
    }

    override fun configure(states: StateMachineStateConfigurer<Status, Action>) {
        states.withStates()
            .initial(Status.PENDING)
            .states(EnumSet.allOf(Status::class.java))
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<Status, Action>) {
        transitions.withExternal()
            .source(Status.PENDING).target(Status.ACTIVE).event(Action.A1)
            .and()
            .withExternal()
            .source(Status.ACTIVE).target(Status.COMPLETED).event(Action.A2)
            .and()
            .withExternal()
            .source(Status.COMPLETED).target(Status.PENDING).event(Action.A3)
    }

    fun listener(): StateMachineListener<Status, Action> {
        return object : StateMachineListenerAdapter<Status, Action>() {
            override fun stateChanged(from: State<Status, Action>?, to: State<Status, Action>?) {
                logger.info("State changed from ${from?.id} to ${to?.id}")

            }
            override fun stateEntered(state: State<Status, Action>?) {
                logger.info("Entered state ${state?.id}")
            }
            override fun stateMachineStarted(stateMachine: StateMachine<Status, Action>?) {
                logger.info("State machine started")
            }
        }
    }
    @Bean
    fun action(): Action<States, Events> {
        return object : Action<States?, Events?>() {
            fun execute(context: StateContext<States?, Events?>?) {
                // do something
            }
        }
    }
}