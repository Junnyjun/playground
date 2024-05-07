package git.io.statemachine.state

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer
import org.springframework.statemachine.listener.StateMachineListener
import org.springframework.statemachine.listener.StateMachineListenerAdapter
import java.util.*

@Configuration
@EnableStateMachine
class StateMachineConfig: EnumStateMachineConfigurerAdapter<Status, Action>(){
    private val logger = LoggerFactory.getLogger(StateMachineConfig::class.java)

    override fun configure(config: StateMachineConfigurationConfigurer<Status, Action>) {
        config.withConfiguration()
            .autoStartup(true)

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

    @Bean
    fun listener(): StateMachineListener<Status, Action> {
        return StateMachineListenerAdapter<Status, Action>()
            .stateChanged{}
    }
}