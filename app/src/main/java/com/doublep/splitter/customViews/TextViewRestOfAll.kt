package com.doublep.splitter.customViews
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class TextViewRestOfAll@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attrs, defStyle) {
    init {
        val face = Typeface.createFromAsset(context.assets, "font/RestOfAll.otf")
        this.typeface = face

    }
}