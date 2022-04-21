package events.ui.news
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import events.model.ArticlesItem
import events.show.R
import events.show.databinding.ItemNewsBinding
class News_Adapter(var items:List<ArticlesItem?>?) :RecyclerView.Adapter<News_Adapter.viewHolder>(){
    fun changeData(articles: List<ArticlesItem?>?) {
        items=articles
        notifyDataSetChanged()
    }

    class viewHolder(val itemBinding: ItemNewsBinding): RecyclerView.ViewHolder(itemBinding.root) {
        //        val title:TextView=itemView.findViewById(R.id.title)
        //        val author:TextView=itemView.findViewById(R.id.author)
        //        val dateTime:TextView=itemView.findViewById(R.id.dataTime)
        //        val image:ImageView=itemView.findViewById(R.id.imageAD)
    fun bind(item:ArticlesItem?){
        itemBinding.item=item
            itemBinding.invalidateAll()
    }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
//       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
//        return ViewHolder(view)
        val viewDataBinding : ItemNewsBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),R.layout.item_news,parent,false
        )
        return viewHolder(viewDataBinding)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
     val item=items?.get(position)
    //        holder.title.setText(itemHold?.title)
    //        holder.author.setText(itemHold?.author)
    //        holder.dateTime.setText(itemHold?.publishedAt)
        holder.bind(item)
    // Glide.with(holder.itemView).load(item?.urlToImage).into(holder.itemBinding.imageAD)
    }

    override fun getItemCount(): Int {
       return items?.size?:0
    }



}