package com.ivettevaldez.pokedex.global

import androidx.appcompat.app.AppCompatActivity
import com.ivettevaldez.pokedex.global.di.activity.ActivityComponent

open class BaseActivity : AppCompatActivity() {

    private val appComponent
        get() = (application as PokedexApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        appComponent.newActivityComponentBuilder().activity(this).build()
    }

    protected val injector by lazy {
        activityComponent.newPresentationComponent()
    }
}