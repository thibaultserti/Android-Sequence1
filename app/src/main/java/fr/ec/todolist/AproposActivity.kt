package fr.ec.todolist

import android.os.Bundle
import android.widget.FrameLayout

class AproposActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_apropos, activityContainer, true)
    }
}
