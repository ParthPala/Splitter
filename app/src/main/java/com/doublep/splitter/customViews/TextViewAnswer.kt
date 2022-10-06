package com.doublep.splitter.customViews



import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class TextViewAnswer(context: Context, attrs: AttributeSet?, size: Int) :
    AppCompatTextView(context, attrs) {
    init {
        val face = Typeface.createFromAsset(context.assets, "font/AnswerFont.ttf")
        this.typeface = face
    }
}