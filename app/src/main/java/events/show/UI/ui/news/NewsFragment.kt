package events.show.UI.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import events.show.API.API_Manager
import events.show.Constants
import events.show.R
import events.show.UI.News_Adapter
import events.show.UI.ui.categories.Categories
import events.show.model.AllResponse
import events.show.model.ResponseBBCnews
import events.show.model.SourcesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {
    lateinit var recycleview: RecyclerView
    lateinit var prgoreesBar: ProgressBar
    lateinit var tab_layout: TabLayout
    lateinit var category: Categories

    companion object {
        fun getInstance(category: Categories): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intioalization()

        getNewsSources()
    }

    val adapter = News_Adapter(null)
    private fun intioalization() {
        tab_layout = requireView().findViewById(R.id.tabs_Layout)
        prgoreesBar = requireView().findViewById(R.id.ProgressBar)
        recycleview = requireView().findViewById(R.id.Recycle_Home)
        recycleview.adapter = adapter
    }

    private fun getNewsSources() {
        API_Manager.getApis().getSources(Constants.apiKey, category.id)
            .enqueue(object : Callback<AllResponse> {
                override fun onFailure(call: Call<AllResponse>, t: Throwable) {
                    Log.e("data", t.localizedMessage)
                    // Long.e("data",t.localizedMessage)
                }

                override fun onResponse(call: Call<AllResponse>, response: Response<AllResponse>) {
                    prgoreesBar.isVisible = false
                    // Log.e("data",response.body().toString())
                    addSourcesToTablayout(response.body()?.sources)
                }
            })

    }

    private fun addSourcesToTablayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tab = tab_layout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            // it is the sources
            // tag is the pointer such array
            tab_layout.addTab(tab)
        }
        tab_layout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // casting tag to sourceitem
                    val source = tab?.tag as SourcesItem
                    getNewsBySources(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
                    getNewsBySources(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )
        tab_layout.getTabAt(0)?.select()
    }

    //    fun ChangeData(data: List<ArticlesItem?>?) {
//        items=data
//        notifyDataSetChanged()
//    }
    private fun getNewsBySources(sources: SourcesItem) {
        prgoreesBar.isVisible = true
        API_Manager.getApis().getNewsSources(Constants.apiKey, sources.id ?: "")
            .enqueue(object : Callback<ResponseBBCnews> {
                override fun onResponse(
                    call: Call<ResponseBBCnews>,
                    response: Response<ResponseBBCnews>
                ) {
                    prgoreesBar.isVisible = false
                    //response.body().re
                    adapter.ChangeData(response.body()?.articles)
                }

                override fun onFailure(call: Call<ResponseBBCnews>, t: Throwable) {
                    prgoreesBar.isVisible = false
                }

            })
    }
}