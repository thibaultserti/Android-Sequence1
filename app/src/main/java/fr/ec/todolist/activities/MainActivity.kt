package fr.ec.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.utilities.DbWorkerThread
import fr.ec.todolist.R
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.database.User


class MainActivity : BasicActivity() {
    private var db: AppDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        // Layout
        super.onCreate(savedInstanceState)
        super.setupToolBar()
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
        val activityContainer: FrameLayout = findViewById(R.id.layout_container)
        layoutInflater.inflate(R.layout.activity_main, activityContainer, true)

        // DB
        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()
        db = AppDatabase.getInstance(this)

        setListeners()
        displayUsers()

    }


    private fun displayUsers() {
        val task = Runnable {
            val users = db?.UserDao()?.getAll()
            Log.i("azertyo", users.toString())


            viewManager = LinearLayoutManager(this)

            if (users != null) {
                viewAdapter = MyAdapter(users.map { it.pseudo })

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
        mDbWorkerThread.postTask(task)
    }

    private fun setListeners() {
        // buttons
        val buttonOk: Button = findViewById(R.id.buttonOk)
        val buttonDisplay: Button = findViewById(R.id.buttonDisplay)
        val buttonClear: Button = findViewById(R.id.buttonClear)


        buttonDisplay.setOnClickListener {
            val task = Runnable {
                val data = db?.UserDao()?.getAll()
                Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show()
            }
            mDbWorkerThread.postTask(task)
        }
        buttonOk.setOnClickListener {

            val task = Runnable { db?.UserDao()?.insertAll(User("toto")) }
            mDbWorkerThread.postTask(task)
            Toast.makeText(this, "Completed!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ChoixListActivity::class.java)
            startActivity(intent);
        }
        buttonClear.setOnClickListener {
            val task = Runnable { db?.UserDao()?.clear() }
            mDbWorkerThread.postTask(task)
            Toast.makeText(this, "Database cleared!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

}