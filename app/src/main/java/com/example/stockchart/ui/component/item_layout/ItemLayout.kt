package com.example.stockchart.ui.component.item_layout

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.stockchart.R

class ItemLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    init {
        LayoutInflater.from(context).inflate(R.layout.item_layout, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ItemLayout, 0, 0)
            val color = typedArray.getColor(R.styleable.ItemLayout_setColor, Color.TRANSPARENT)
            findViewById<View>(R.id.color_block).setBackgroundColor(color)
            typedArray.recycle()
        }

        val text = context.getString(R.string.item_empty_string)
        findViewById<TextView>(R.id.text_legend).text = text
    }

    fun setText(text: String) {
        findViewById<TextView>(R.id.text_legend).text = text
    }
}