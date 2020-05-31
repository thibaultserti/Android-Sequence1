package fr.ec.todolist.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.PreferenceActivity
import android.preference.PreferenceManager
import android.widget.EditText
import android.widget.Toast
import fr.ec.todolist.R
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        var pseudo :String? = intent.getStringExtra("pseudo")
        Toast.makeText(this, pseudo, Toast.LENGTH_SHORT).show()
        addPreferencesFromResource(R.xml.userpreferences);
        val pseudoPreference : EditTextPreference? = findPreference("edit_text_preference_1") as EditTextPreference?
        pseudoPreference?.setDialogMessage(pseudo)



        }
    }


