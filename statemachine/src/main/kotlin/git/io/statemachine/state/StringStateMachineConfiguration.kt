package git.io.statemachine.state

import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.action.Action
import org.springframework.statemachine.config.EnableStateMachine
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer

@Configuration
@EnableStateMachine
class StringStateMachineConfiguration : StateMachineConfigurerAdapter<String, String>() {
    private val log: Logger = getLogger(StringStateMachineConfiguration::class.java)
    override fun configure(states: StateMachineStateConfigurer<String, String>?) {
        states
            ?.withStates()
            ?.initial("START")
            ?.end("END")
            ?.states(setOf("S1", "S2", "S3"))
            ?: throw IllegalStateException("State configuration is required")
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<String, String>?) {
        transitions
            ?.withExternal()
            ?.source("START")?.target("S1")?.event("E1")?.action(initAction())?.and()
            ?.withExternal()
            ?.source("S1")?.target("S2")?.event("E2")?.action(updateToS2())?.and()
            ?.withExternal()
            ?.source("S2")?.target("S3")?.event("EE")?.action(updateToS3())?.and()

            ?: throw IllegalStateException("Transition configuration is required")
    }

    @Bean
    fun initAction() : Action<String,String> {
        return Action<String,String> { context ->
            log.info("State machine started -> ${context.source.id} to ${context.target.id}")
        }
    }

    @Bean
    fun updateToS2() : Action<String,String> {
        return Action<String,String> { context ->
            log.info("State machine updated to S2-> ${context.source.id} to ${context.target.id}")
        }
    }

    @Bean
    fun updateToS3() : Action<String,String> {
        return Action<String,String> { context ->
            log.info("State machine updated to S3-> ${context.source.id} to ${context.target.id}")
        }
    }
}