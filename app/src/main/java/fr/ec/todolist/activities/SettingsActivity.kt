package fr.ec.todolist.activities

import android.os.Bundle
import android.preference.PreferenceActivity
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R

class SettingsActivity : PreferenceActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.userpreferences);

        }
    }


