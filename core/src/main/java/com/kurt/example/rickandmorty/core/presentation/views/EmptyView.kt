package com.kurt.example.rickandmorty.core.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.kurt.example.rickandmorty.core.R

/**
 * Copyright (c) 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 16/04/2019
 */
class EmptyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {
    private val txtTitle by lazy { findViewById<AppCompatTextView>(R.id.txt_empty_title) }
    private val txtDescription by lazy { findViewById<AppCompatTextView>(R.id.txt_empty_description) }

    init {
        LayoutInflater.from(this.context).inflate(R.layout.view_empty, this, true)
        if (attrs != null) {
            context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EmptyView,
                0, 0
            ).apply {
                try {
                    title = getString(R.styleable.EmptyView_title) ?: ""
                    description = getString(R.styleable.EmptyView_description) ?: ""
                } finally {
                    recycle()
                }
            }
        }
    }

    var title: String = ""
        set(value) {
            field = value
            txtTitle.text = value
        }
        get() = txtTitle.text.toString()

    var description: String = ""
        set(value) {
            field = value
            txtDescription.text = value
        }
        get() = txtDescription.text.toString()
}