package fr.ec.todolist

import android.os.Bundle

class ShowListActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
    }
}
