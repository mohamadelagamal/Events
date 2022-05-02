package app_note.ui.fragment.adapter

import app_note.ui.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app_note.model.entity.Todo
import java.lang.Exception
import java.lang.IllegalArgumentException

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
        if(onItemClickListener!=null){
            try {
                holder.makeDone.setOnClickListener{
                 onItemClickListener!!.onItemclike(position)
            }
            }catch (ex:Exception){
                throw IllegalArgumentException(ex.toString())
            }
        }

    }

    var onItemClickListener : OnItemClick?=null
    interface OnItemClick{
        fun onItemclike(pos:Int)
    }

    override fun getItemCount(): Int =items?.size ?:0
}