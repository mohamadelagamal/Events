package events.ui
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import events.model.ArticlesItem
import events.show.R

class News_Adapter(var items:List<ArticlesItem?>?) :RecyclerView.Adapter<News_Adapter.ViewHolder>(){
    fun ChangeData(articles: List<ArticlesItem?>?) {
        items=articles
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
        val title:TextView=itemView.findViewById(R.id.title)
        val author:TextView=itemView.findViewById(R.id.author)
        val dateTime:TextView=itemView.findViewById(R.id.dataTime)
        val image:ImageView=itemView.findViewById(R.id.imageAD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val itemHold=items?.get(position)
        holder.title.setText(itemHold?.title)
        holder.author.setText(itemHold?.author)
        holder.dateTime.setText(itemHold?.publishedAt)
        Glide.with(holder.itemView).load(itemHold?.urlToImage).into(holder.image)
    }

    override fun getItemCount(): Int {
       return items?.size?:0
    }



}