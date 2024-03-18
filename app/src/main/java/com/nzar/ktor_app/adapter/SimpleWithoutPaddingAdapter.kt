package com.nzar.ktor_app.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

class SimpleWithoutPaddingAdapter(context: Context, items: Array<CharSequence>) :
    ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        super.getView(position, convertView, parent).apply {
            setPadding(0, paddingTop, 0, paddingBottom)
        }
}
