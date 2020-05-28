package fr.ec.todolist.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.activities.ShowListActivity

class TodoListAdapter(private val list: List<String>, private val pseudo: String) : RecyclerView.Adapter<TodoListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position], pseudo)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: String, pseudo: String) {
            val textViewName = itemView.findViewById(R.id.name) as TextView
            textViewName.text = item
            val card = itemView.findViewById(R.id.list_users) as CardView
            card.setOnClickListener {

                val intent = Intent(itemView.context, ShowListActivity::class.java)
                intent.putExtra("liste", item)
                intent.putExtra("pseudo", pseudo)
                itemView.context.startActivity(intent)
            }
        }
    }
}