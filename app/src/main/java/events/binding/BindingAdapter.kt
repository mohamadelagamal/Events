package events

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageFromUrl(imageView:ImageView,url:String){
    Glide.with(imageView).load(url).into(imageView)
}