package fr.ec.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import fr.ec.todolist.R

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_main, activityContainer, true)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(this, ChoixListActivity::class.java)
            startActivity(intent);
        }
    }
}
