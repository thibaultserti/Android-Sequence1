package fr.ec.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

open class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic)
    }
}
