package io.getstream.streamchat1.ui

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun <A: Activity> Activity.startNewActivity(activity: Class<A>){
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun Fragment.snackbar(message: String, retry:(()->Unit)? = null){
    Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).also {
        it.setAction("Ok"){
            retry?.invoke()
        }
    }.show()
}