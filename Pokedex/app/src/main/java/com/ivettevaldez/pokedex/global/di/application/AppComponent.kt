package com.ivettevaldez.pokedex.global.di.application

import com.ivettevaldez.pokedex.global.PokedexApplication
import com.ivettevaldez.pokedex.global.di.activity.ActivityComponent
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: PokedexApplication): Builder
        fun appModule(appModule: AppModule): Builder
        fun build(): AppComponent
    }
}