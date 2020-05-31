package fr.ec.todolist.activities

import android.app.Activity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.adapters.LanguageAdapter

class SettingsActivity : BasicActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        setContentView(R.layout.activity_settings)
        layoutInflater.inflate(R.layout.activity_settings, activityContainer, true)
        viewManager = LinearLayoutManager(this)
        viewAdapter = LanguageAdapter()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView2).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager



        }
    }
}

