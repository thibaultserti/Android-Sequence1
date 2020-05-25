package fr.ec.todolist.activities

import android.os.Bundle
import android.widget.FrameLayout
import fr.ec.todolist.R

class AproposActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_apropos, activityContainer, true)
    }
}
