package com.ivettevaldez.pokedex.ui.common.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.ivettevaldez.pokedex.R

class CustomToolbar : Toolbar {

    interface NavigateUpListener {
        fun onNavigationUpClicked()
    }

    private var navigateUpListener: () -> Unit = {}

    private lateinit var textTitle: TextView
    private lateinit var buttonNavigateUp: ImageButton

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true)
        setContentInsetsRelative(0, 0)
        textTitle = view.findViewById(R.id.text_title)
        buttonNavigateUp = view.findViewById(R.id.button_navigate_up)
        buttonNavigateUp.setOnClickListener { navigateUpListener.invoke() }
    }

    fun setTitle(title: String) {
        textTitle.text = title
    }

    fun setNavigateUpListener(navigateUpListener: () -> Unit) {
        this.navigateUpListener = navigateUpListener
        buttonNavigateUp.visibility = View.VISIBLE
    }
}