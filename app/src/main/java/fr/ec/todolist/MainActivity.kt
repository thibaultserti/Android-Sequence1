package fr.ec.todolist

import android.os.Bundle
import android.widget.FrameLayout

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_main, activityContainer, true)
    }
}
