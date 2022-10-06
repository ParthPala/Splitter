package com.doublep.splitter.customViews
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

class TextViewQuestion @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
) : AppCompatTextView(context, attrs, defStyle)
{
    init {
        val face = Typeface.createFromAsset(context.applicationContext.assets, "font/QuestionFont.ttf")
        this.typeface = face
    }
}