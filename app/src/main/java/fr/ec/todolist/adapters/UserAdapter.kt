package fr.ec.todolist.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.activities.ChoixListActivity
import fr.ec.todolist.database.user.User

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: User) {
            val textViewName = itemView.findViewById(R.id.name) as TextView
            textViewName.text = user.pseudo
            val card = itemView.findViewById(R.id.list_users) as CardView
            card.setOnClickListener {
                val intent = Intent(itemView.context, ChoixListActivity::class.java)
                intent.putExtra("pseudo", user.pseudo)
                itemView.context.startActivity(intent);
            }
        }
    }
}