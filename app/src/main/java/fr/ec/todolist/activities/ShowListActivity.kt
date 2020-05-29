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
import fr.ec.todolist.adapters.ItemAdapter
import fr.ec.todolist.adapters.TodoListAdapter
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.database.item.Item
import fr.ec.todolist.utilities.DbWorkerThread

class ShowListActivity : BasicActivity() {
    private var db: AppDatabase? = null

    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var pseudo: String? = null
    private var liste: String? = null

    private val mUiHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_show_list, activityContainer, true)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        db = AppDatabase.getInstance(this)

        pseudo = intent.getStringExtra("pseudo")
        liste = intent.getStringExtra("liste")

        setListeners()
        displayItem(pseudo, liste)
    }

    private fun bindDataWithUi(listes: List<Item>?) {
        viewManager = LinearLayoutManager(this)

        if (listes != null) {//db?.itemDao()?.update(item)
            viewAdapter = ItemAdapter(listes, onClickListener = { item ->
                item.checked = !(item.checked)
                val task = Runnable {
                    db?.itemDao()?.update(item)
                }
                mDbWorkerThread.postTask(task)

            })

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

    private fun displayItem(pseudo: String?, liste: String?) {
        val task = Runnable {
            val listes = db?.itemDao()?.getItems(pseudo!!, liste!!)
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
                                name = name.toString(),
                                owner = pseudo ?: "",
                                liste = liste ?: ""
                            )
                        )
                }
                mDbWorkerThread.postTask(task)
                Toast.makeText(this, "Completed!", Toast.LENGTH_SHORT).show()

            }
        }
    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        upIntent?.putExtra("pseudo", pseudo)
        return super.navigateUpTo(upIntent)
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}
