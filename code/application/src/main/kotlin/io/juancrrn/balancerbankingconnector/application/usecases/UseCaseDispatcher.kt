package io.juancrrn.balancerbankingconnector.application.usecases

import io.juancrrn.balancerbankingconnector.application.commands.Command
import io.juancrrn.balancerbankingconnector.application.commands.CommandUseCase
import io.juancrrn.balancerbankingconnector.application.queries.Query
import io.juancrrn.balancerbankingconnector.application.queries.QueryUseCase
import org.springframework.stereotype.Component
import kotlin.reflect.KClassifier

/**
 * Resolves and dispatches a Use Case based on the [Command] or [Query] provided.
 *
 * @param commandUseCases the command use cases instances
 * @param queryUseCases the query use cases instances
 */
@Component
class UseCaseDispatcher(
    commandUseCases: List<CommandUseCase<*, *>>,
    queryUseCases: List<QueryUseCase<*, *>>,
) {

    @Suppress("UNCHECKED_CAST")
    private val commandUseCasesMap: Map<KClassifier, CommandUseCase<Command<*>, *>> =
        commandUseCases.associateBy { it::class.supertypes[0].arguments[0].type!!.classifier!! }
            as Map<KClassifier, CommandUseCase<Command<*>, *>>

    @Suppress("UNCHECKED_CAST")
    private val queryUseCasesMap: Map<KClassifier, QueryUseCase<Query<*>, *>> =
        queryUseCases.associateBy { it::class.supertypes[0].arguments[0].type!!.classifier!! }
            as Map<KClassifier, QueryUseCase<Query<*>, *>>

    /**
     * Dispatches a [CommandUseCase] based on the [command].
     *
     * @param command the command to dispatch
     * @return the metadata output of the command
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun <R> dispatch(command: Command<R>): R {
        val useCase = commandUseCasesMap[command::class]
            ?: throw IllegalStateException("Use Case not found for this command.")

        return useCase.dispatch(command) as R
    }

    /**
     * Dispatches a [QueryUseCase] based on the [query].
     *
     * @param query the query to dispatch
     * @return the output of the query
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun <R> dispatch(query: Query<R>): R {
        val useCase = queryUseCasesMap[query::class]
            ?: throw IllegalStateException("Use Case not found for this query.")

        return useCase.dispatch(query) as R
    }
}
