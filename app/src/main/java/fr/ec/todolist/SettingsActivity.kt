package fr.ec.todolist

import android.os.Bundle
import android.widget.FrameLayout

class SettingsActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_settings, activityContainer, true)
    }
}
