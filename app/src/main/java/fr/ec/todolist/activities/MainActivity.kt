package fr.ec.todolist.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.utilities.DbWorkerThread
import fr.ec.todolist.R
import fr.ec.todolist.adapters.UserAdapter
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.database.user.User


class MainActivity : BasicActivity() {
    private var db: AppDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val mUiHandler = Handler()
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

    private fun bindDataWithUi(users: List<User>?) {
        viewManager = LinearLayoutManager(this)

        if (users != null) {
            viewAdapter = UserAdapter(users)

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

    private fun displayUsers() {
        val task = Runnable {
            val users = db?.userDao()?.getAll()
            mUiHandler.post { bindDataWithUi(users) }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun setListeners() {
        // buttons
        val buttonOk: Button = findViewById(R.id.buttonOk)
        val buttonClear: Button = findViewById(R.id.buttonClear)


        buttonOk.setOnClickListener {
            val pseudo = findViewById<EditText>(R.id.pseudoEdit).text
            val pseudoEdithint = findViewById<EditText>(R.id.pseudoEdit).hint
            if (pseudo.toString() != "") {

                val task = Runnable {
                    db?.userDao()?.insertUsers(
                        User(
                            pseudo = pseudo.toString()
                        )
                    )
                }
                mDbWorkerThread.postTask(task)
                Toast.makeText(this, "Completed!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ChoixListActivity::class.java)
                intent.putExtra("pseudo", pseudo.toString())
                startActivity(intent);
            }


        }
        buttonClear.setOnClickListener {
            val task = Runnable { db?.clearAllTables() }
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