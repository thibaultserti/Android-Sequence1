package fr.ec.todolist.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.adapters.ListListAdapter
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.utilities.DbWorkerThread

class ChoixListActivity : BasicActivity() {
    private var db: AppDatabase? = null

    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val mUiHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_choix_list, activityContainer, true)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        db = AppDatabase.getInstance(this)

        val pseudo = intent.getStringExtra("pseudo")
       displayListe(pseudo)
    }

    private fun bindDataWithUi(users: List<String>?) {
        viewManager = LinearLayoutManager(this)

        if (users != null) {
            viewAdapter = ListListAdapter(users)

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
            val users = pseudo?.let { db?.UserDao()?.getListe(it) }
            mUiHandler.post { bindDataWithUi(users) }
        }
        mDbWorkerThread.postTask(task)
    }
}
