package fr.ec.todolist.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.*
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.internal.ContextUtils.getActivity
import fr.ec.todolist.R
import kotlinx.android.synthetic.main.activity_main.*

class SettingsActivity : PreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        var pseudo :String? = intent.getStringExtra("pseudo")
        addPreferencesFromResource(R.xml.userpreferences);
        val pseudoPreference : EditTextPreference? = findPreference("edit_text_preference_1") as EditTextPreference?
        pseudoPreference?.setDialogMessage(pseudo)
        val doyouliketheapp : CheckBoxPreference = findPreference("checkbox1") as CheckBoxPreference
        doyouliketheapp.setOnPreferenceChangeListener(object: Preference.OnPreferenceChangeListener
        {
            override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
                val boolean_happy : Boolean  = !doyouliketheapp.isChecked
                if (boolean_happy)
                    Toast.makeText(applicationContext, "Thank you !", Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(applicationContext, "It's alright, we will do better next time !", Toast.LENGTH_SHORT).show()
                return true
            }
        })



        }
    }


