package com.harsh.githubClient.util

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.harsh.githubClient.R

fun View.showMessage(message: Message) {
    val snackBar = Snackbar.make(this, message.message, Snackbar.LENGTH_LONG)
    if (message.caution)
        snackBar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.colorAccent))
    else
        snackBar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.colorPrimary))
    snackBar.show()
}

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}
