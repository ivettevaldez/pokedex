package com.ivettevaldez.pokedex.ui

import android.os.Bundle
import com.ivettevaldez.pokedex.global.BaseActivity
import com.ivettevaldez.pokedex.ui.common.viewbinding.ViewBindingFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewBindingFactory: ViewBindingFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)

        val binding = viewBindingFactory.newMainBinding()
        setContentView(binding.root)
    }
}