package com.example.activitytest.four.base

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.example.activitytest.R

class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.title, this)
        rootView.findViewById<Button>(R.id.titleBack).setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }

        rootView.findViewById<Button>(R.id.titleEdit).setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_LONG).show()
        }
    }
}