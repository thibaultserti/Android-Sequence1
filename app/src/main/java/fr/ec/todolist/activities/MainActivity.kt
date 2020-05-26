package fr.ec.todolist.activities

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import fr.ec.todolist.R
import fr.ec.todolist.database.AppDatabase
import fr.ec.todolist.database.User


class MainActivity : BasicActivity() {
    private var db: AppDatabase? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
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

        // buttons
        val button: Button = findViewById(R.id.button)
        val b: Button = findViewById(R.id.buttonDisplay)

        b.setOnClickListener {
            var d : List<User>? = null
            val task = Runnable { val data = db?.UserDao()?.getAll()
                d = data
                Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT ).show()

            }
            mDbWorkerThread.postTask(task)
        }
        button.setOnClickListener {

            val task = Runnable { db?.UserDao()?.insertAll(User("toto")) }
            mDbWorkerThread.postTask(task)
            Toast.makeText(this,"Completed!", Toast.LENGTH_SHORT ).show()
            //val intent = Intent(this, ChoixListActivity::class.java)
            //startActivity(intent);
        }
    }
    override fun onDestroy() {
        AppDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

}
class DbWorkerThread(threadName: String) : HandlerThread(threadName) {

    private lateinit var mWorkerHandler: Handler

    override fun onLooperPrepared() {
        super.onLooperPrepared()
        mWorkerHandler = Handler(looper)
    }

    fun postTask(task: Runnable) {
        mWorkerHandler.post(task)
    }

}