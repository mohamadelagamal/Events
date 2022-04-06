package events.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import events.show.R

class CategoriesFragment : Fragment() {

    //item from API
    val categoriesList= listOf(
    Categories(
        "sports",R.string.sports,R.drawable.sports,R.color.red
    ),
        Categories(
            "entertainment",R.string.technology,R.drawable.politics,R.color.blue
        ),
        Categories(
            "business",R.string.business,R.drawable.bussines,R.color.brown_orange
        ),
        Categories(
            "general",R.string.general,R.drawable.environment,R.color.baby_blue
        ),
        Categories(
            "science",R.string.science,R.drawable.science,R.color.yellow
        ),
        Categories(
            "health",R.string.health,R.drawable.health, R.color.pink
        ),
    )
    val adapter=CategoriesAdapter(categoriesList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }
    lateinit var recycleView:RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView=view.findViewById(R.id.recycle_view)
        recycleView.adapter=adapter
        adapter.onItemClickListener= object :CategoriesAdapter.OnItemCLickListener{
            override fun onItemClick(pos: Int, categories: Categories) {
            onCategoryClikeListerner?.onCategoryCLick(categories)
            }
        }
    }

    var onCategoryClikeListerner:OnCategoryCLickListener?=null
    interface OnCategoryCLickListener{
        fun onCategoryCLick(categories: Categories)
    }
}