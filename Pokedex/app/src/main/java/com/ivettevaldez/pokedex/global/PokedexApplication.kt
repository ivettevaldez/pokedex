package com.ivettevaldez.pokedex.global

import android.app.Application
import com.ivettevaldez.pokedex.global.di.application.AppComponent
import com.ivettevaldez.pokedex.global.di.application.DaggerAppComponent

class PokedexApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}