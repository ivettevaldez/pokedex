package com.ivettevaldez.pokedex.global.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.ivettevaldez.pokedex.global.resources.Resource.Status.FAILURE
import com.ivettevaldez.pokedex.global.resources.Resource.Status.SUCCESS
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

open class ResourceFetcher @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher
) {

    open fun <T> fetchLocalData(dbQuery: () -> LiveData<T>): LiveData<Resource<T>> =
        liveData(ioDispatcher) {
            emit(Resource.loading())
            val source = dbQuery.invoke().map {
                Resource.success(it)
            }
            emitSource(source)
        }

    open fun <T, A> fetchData(
        dbQuery: () -> LiveData<T>,
        networkCall: suspend () -> Resource<A>,
        saveCallResult: suspend (A) -> Unit
    ): LiveData<Resource<T>> = liveData(ioDispatcher) {
        emit(Resource.loading())
        val source = dbQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == FAILURE) {
            emit(Resource.failure(responseStatus.message!!))
            emitSource(source)
        }
    }
}