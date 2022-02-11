package events.show.UI.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.google.android.material.card.MaterialCardView
import events.show.R

class CategoriesAdapter(val categories: List<Categories>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {
     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title:TextView=itemView.findViewById(R.id.title)
         val image:ImageView=itemView.findViewById(R.id.imageView)
         val materialCard:MaterialCardView=itemView.findViewById(R.id.material_card)
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(
            if(viewType==Left_SIDED_VIEW_TYpE)
            R.layout.left_sided_categories else R.layout.right_sided_categories,parent,false)
        return ViewHolder(view)
    }

    val Left_SIDED_VIEW_TYpE=10
    val RIGHT_SIDED_VIEW_TYpE=20
    override fun getItemViewType(position: Int): Int {
        return if(position%2==0) Left_SIDED_VIEW_TYpE else RIGHT_SIDED_VIEW_TYpE
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=categories[position]
        holder.title.setText(item.titleId)
        holder.image.setImageResource(item.imageViewId)
        holder.materialCard.setCardBackgroundColor(holder.itemView.context.getColor(item.backgroundID))
        onItemClickListener?.let {
            // not nall this is let
            holder.itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position,item)
            }
        }
    }

    override fun getItemCount(): Int {
    return categories.size
    }

    var onItemClickListener:OnItemCLickListener?=null
    interface OnItemCLickListener {
        fun onItemClick(pos:Int,categories:Categories)
    }
}