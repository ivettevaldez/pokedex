package com.ivettevaldez.pokedex.global

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected val injector by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }
}