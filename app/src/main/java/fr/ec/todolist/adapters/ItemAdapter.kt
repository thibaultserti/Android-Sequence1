package fr.ec.todolist.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import fr.ec.todolist.R
import fr.ec.todolist.database.item.Item

class ItemAdapter(
    private val list: MutableList<Item>, private val onClickListener: (Item) -> Unit
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.checkbox_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
        holder.itemView.setOnClickListener {
            onClickListener.invoke(list[position])
        }
    }


    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return list.size
    }


    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: Item) {
            val checkbox = itemView.findViewById(R.id.checkBox) as CheckBox
            checkbox.text = item.name
            checkbox.isChecked = item.checked
        }
    }
}