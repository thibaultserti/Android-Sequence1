package fr.ec.todolist

import android.os.Bundle
import android.util.Log

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
