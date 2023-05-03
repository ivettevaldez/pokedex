package com.ivettevaldez.pokedex.ui.common.images

import android.net.Uri
import android.widget.ImageView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener
import com.ivettevaldez.pokedex.global.BaseActivity
import com.ivettevaldez.pokedex.global.di.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class ImageLoader @Inject constructor(private val activity: BaseActivity) {

    fun loadImage(imageUrl: String, target: ImageView, callback: (() -> Unit)? = null) {
        GlideToVectorYou
            .init()
            .with(activity)
            .withListener(object : GlideToVectorYouListener {
                override fun onLoadFailed() {
                    callback?.invoke()
                }

                override fun onResourceReady() {
                    callback?.invoke()
                }
            })
            .load(Uri.parse(imageUrl), target)
    }
}