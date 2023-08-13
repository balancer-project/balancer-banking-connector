package io.juancrrn.balancerbankingconnector.queries

/**
 * Dispatches a [Query] returning an output.
 *
 * @param Q the query to dispatch
 * @param R the result (output) of the query
 */
interface QueryUseCase<Q : Query<R>, out R> {

    /**
     * Dispatches a [Query] returning a result.
     *
     * @param query the query to dispatch
     * @return the result (output) of the query
     */
    suspend fun dispatch(query: Q): R
}
