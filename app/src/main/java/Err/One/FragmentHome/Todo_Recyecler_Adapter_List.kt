package Err.One.FragmentHome

import Err.One.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.route.todo_c35_sat.database.model.Todo

class Todo_Recyecler_Adapter_List(var items:MutableList<Todo>?)  : RecyclerView.Adapter<Todo_Recyecler_Adapter_List.viewHolder>() {

    class viewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val title :TextView= itemView.findViewById(R.id.title_Done)
        val descraption:TextView=itemView.findViewById(R.id.description_Done)
        val makeDone:ImageView=itemView.findViewById(R.id.make_Done)

    }

    fun ChangeData(newItems:MutableList<Todo>){
        items=newItems
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_todo_recycle,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val item = items!!.get(position)
        holder.title.setText(item.name)
        holder.descraption.setText(item.details)



    }

    override fun getItemCount(): Int =items?.size ?:0
}