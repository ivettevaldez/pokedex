package com.ivettevaldez.pokedex.ui.common.messages

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.ivettevaldez.pokedex.R
import com.ivettevaldez.pokedex.global.BaseActivity
import com.ivettevaldez.pokedex.global.di.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class SnackBarHelper @Inject constructor(private val activity: BaseActivity) {

    fun showSuccess(message: String, rootView: ViewGroup) {
        showSnackBar(message, rootView, R.color.success_color)
    }

    fun showError(message: String, rootView: ViewGroup) {
        showSnackBar(message, rootView, R.color.error_color)
    }

    private fun showSnackBar(message: String, rootView: ViewGroup, @ColorRes color: Int) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).apply {
            view.setBackgroundColor(ContextCompat.getColor(activity, color))
        }.show()
    }
}