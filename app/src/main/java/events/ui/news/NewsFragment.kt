package events.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import events.model.ArticlesItem
import events.model.SourcesItem
import events.show.R
import events.show.databinding.FragmentNewsBinding
import events.ui.categories.Categories

class NewsFragment : Fragment() {

    lateinit var category: Categories
    lateinit var viewModel:NewsViewModel
    lateinit var viewDataBinding: FragmentNewsBinding
    companion object {
        fun getInstance(category: Categories): NewsFragment {
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //..... to init viewModel
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // return inflater.inflated(R.layout.fragment_news, container, false)
      viewDataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_news,container,false)
        //... root is the root element inflation
    return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        intioalization()
        susbscribeToLiveData()
        viewModel.getNewsSources(category)
    }

     fun susbscribeToLiveData() {
         // ... we used viewLifecycleOwner because we actually in fragment lifeData don't send update without change in ui
        viewModel.sourcesLiveData.observe(viewLifecycleOwner,{
            addSourcesToTablayout(it)
        })
         viewModel.newsLiveData.observe(viewLifecycleOwner,{
         showNews(it)
          })
         viewModel.progressVisible.observe(viewLifecycleOwner,{ isVisible->
             when {
                 isVisible -> viewDataBinding.ProgressBar.visibility = View.VISIBLE

                 else ->  viewDataBinding.ProgressBar.visibility = View.GONE
             }
         })
         viewModel.messageLiveData.observe(viewLifecycleOwner,{
             Toast.makeText(activity,it,Toast.LENGTH_LONG).show()
         })
     }

     fun showNews(newsList: List<ArticlesItem?>?) {
     adapter.changeData(newsList)
     }

    val adapter = News_Adapter(null)
    private fun intioalization() {
//        tab_layout = requireView().findViewById(R.id.tabs_Layout)
//        progressBar = requireView().findViewById(R.id.ProgressBar)
//        recycleview = requireView().findViewById(R.id.Recycle_Home)
        viewDataBinding.RecycleHome.adapter = adapter
    }

    private fun addSourcesToTablayout(sources: List<SourcesItem?>?) {

        sources?.forEach {
            val tab = viewDataBinding.tabsLayout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            // it is the sources
            // tag is the pointer such array
            viewDataBinding.tabsLayout.addTab(tab)
        }
        viewDataBinding.tabsLayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // casting tag to sourceitem
                    val source = tab?.tag as SourcesItem
                    viewModel.getNewsBySources(source)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source = tab?.tag as SourcesItem
//                    getNewsBySources(source)

                    viewModel.getNewsBySources(source)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )
        viewDataBinding.tabsLayout.getTabAt(0)?.select()
    }
}