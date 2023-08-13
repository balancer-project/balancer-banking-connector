package io.juancrrn.balancerbankingconnector.commands

/**
 * Dispatches a [Command] returning a metadata output.
 *
 * @param C the command to dispatch
 * @param R the metadata output of the command
 */
interface CommandUseCase<C : Command<R>, out R> {

    /**
     * Dispatches a [Command].
     *
     * @param command the command to dispatch
     * @return the metadata output of the command
     */
    suspend fun dispatch(command: C): R
}
