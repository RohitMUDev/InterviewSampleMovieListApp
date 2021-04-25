package com.example.movielist.util

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.movielist.R

class CustomTextView(context : Context, attributeSet: AttributeSet) : AppCompatTextView(context, attributeSet)  {

    var text = ""
    var background = R.color.white
    init {

        val attributes =  context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView)

        try {
            val text = attributes.getResourceId(R.styleable.CustomTextView_title, 0)
            setText(text)

            val background =  attributes.getColor(R.styleable.CustomTextView_text_background, resources.getColor(R.color.colorAccent))
            setBackgroundColor(background)
        }catch (e : Exception){

        }finally {

            attributes.recycle()
        }
    }




}