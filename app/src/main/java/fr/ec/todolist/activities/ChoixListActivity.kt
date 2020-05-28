package fr.ec.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.adapters.ListListAdapter
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.database.item.Item
import fr.ec.todolist.utilities.DbWorkerThread

class ChoixListActivity : BasicActivity() {
    private var db: AppDatabase? = null

    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var pseudo: String? = null

    private val mUiHandler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_choix_list, activityContainer, true)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        db = AppDatabase.getInstance(this)

        pseudo = intent.getStringExtra("pseudo")

        setListeners()
        displayListe(pseudo)
    }

    private fun bindDataWithUi(listes: List<String>?) {
        viewManager = LinearLayoutManager(this)

        if (listes != null) {
            viewAdapter = ListListAdapter(listes)

            recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                setHasFixedSize(true)

                // use a linear layout manager
                layoutManager = viewManager

                // specify an viewAdapter (see also next example)
                adapter = viewAdapter
            }
        }
    }

    private fun displayListe(pseudo: String?) {
        val task = Runnable {
            val listes = pseudo?.let { db?.itemDao()?.getLists(it) }
            mUiHandler.post { bindDataWithUi(listes) }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun setListeners() {
        val buttonAdd: Button = findViewById(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val name = findViewById<EditText>(R.id.editTextList).text
            if (name.toString() != "") {

                val task = Runnable {
                    db?.itemDao()
                        ?.insertItems(
                            Item(
                                name = "Item 1",
                                owner = pseudo ?: "",
                                liste = name.toString()
                            )
                        )
                }
                mDbWorkerThread.postTask(task)
                Toast.makeText(this, "Completed!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ShowListActivity::class.java)
                intent.putExtra("liste", name.toString())
                intent.putExtra("pseudo", pseudo)

                startActivity(intent);
            }


        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}
