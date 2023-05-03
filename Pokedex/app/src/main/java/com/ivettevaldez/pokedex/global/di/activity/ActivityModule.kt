package com.ivettevaldez.pokedex.global.di.activity

import android.view.LayoutInflater
import com.ivettevaldez.pokedex.global.BaseActivity
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    fun layoutInflater(activity: BaseActivity): LayoutInflater = LayoutInflater.from(activity)
}