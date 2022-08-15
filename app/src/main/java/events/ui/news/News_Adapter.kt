package events.ui.news
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.ArticlesItemDTO
import events.show.R
import events.show.databinding.ItemNewsBinding
class News_Adapter(var items:List<ArticlesItemDTO?>?) :RecyclerView.Adapter<News_Adapter.viewHolder>(){
    fun changeData(articles: List<ArticlesItemDTO?>?) {
        items=articles
        notifyDataSetChanged()
    }

    class viewHolder(val itemBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(item:ArticlesItemDTO?){
            itemBinding.item=item
            itemBinding.invalidateAll()
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val viewDataBinding : ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),R.layout.item_news,parent,false
        )
        return viewHolder(viewDataBinding)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
     val item=items?.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return items?.size?:0
    }



}