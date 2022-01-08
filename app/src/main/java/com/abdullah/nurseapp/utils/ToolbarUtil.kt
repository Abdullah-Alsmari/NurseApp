package com.abdullah.nurseapp.utils

import android.content.Context
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

fun setToolbarTitleWithBackButton(context: Context, toolbar: Toolbar, title: String) {

    toolbar.title = title
    (context as AppCompatActivity).setSupportActionBar(toolbar)
    val actionBar: ActionBar? = (context).supportActionBar
    toolbar.setNavigationOnClickListener { view: View? -> (context as AppCompatActivity).onBackPressed() }
    if (actionBar != null) {
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayUseLogoEnabled(false)
    }
}


fun setToolbarTitle(context: Context, toolbar: Toolbar, title: String) {

    toolbar.title = title
    (context as AppCompatActivity).setSupportActionBar(toolbar)
    val actionBar: ActionBar? = (context).supportActionBar
    toolbar.setNavigationOnClickListener { view: View? -> (context as AppCompatActivity).onBackPressed() }
    if (actionBar != null) {
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayUseLogoEnabled(false)
    }
}





