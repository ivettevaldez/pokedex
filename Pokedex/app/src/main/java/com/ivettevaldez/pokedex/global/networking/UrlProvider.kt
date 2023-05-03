package com.ivettevaldez.pokedex.global.networking

import com.ivettevaldez.pokedex.global.di.application.AppScope
import javax.inject.Inject

@AppScope
class UrlProvider @Inject constructor() {

    fun getBaseUrl(): String = Constants.BASE_URL
}