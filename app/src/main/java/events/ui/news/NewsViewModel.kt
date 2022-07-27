package events.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import events.NetworkHandler
import events.model.ArticlesItem
import events.model.SourcesItem
import events.repos.news.NewsOnlineDataSource
import events.repos.news.NewsOnlineDataSourceImpl
import events.repos.news.NewsRepository
import events.repos.news.NewsRepositoryImpl
import events.repos.source.*
import events.room.MyDataBase
import events.ui.Constants
import events.ui.categories.Categories
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var newsRepository:NewsRepository,
    var sourcesRepository: SourceRepository
): ViewModel() {
    //...to change data
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val progressVisible = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()
    // this reposatory

//    lateinit var newsONlineDataSource:NewsOnlineDataSource
//    lateinit var sourcesOnlineDataSource : SourcesOnlineDataSource
//    lateinit var sourcesOfflineDataSource:SourcesOfflineDataSource
//    lateinit var networkHandler: NetworkHandler
//    init {
//        newsONlineDataSource = NewsOnlineDataSourceImpl(API_Manager.getApis())
//        newsRepository = NewsRepositoryImpl(newsONlineDataSource)
//        sourcesOfflineDataSource = SourcesOfflineDataSourceImpl(MyDataBase.getInstance())
//        sourcesOnlineDataSource = SourcesOnlineDataSourceImpl(API_Manager.getApis())
//        sourcesRepository = SourcesRepositoryImpl(sourcesOnlineDataSource,sourcesOfflineDataSource,
//        // how to check internet status
//            Constants.networkHandler
//            )
//    }
    //... coroutines
    fun getNewsSources(category: Categories) {
        // used coroutines
        viewModelScope.launch {
            try {
                // use repository
                progressVisible.value=true
                val result = sourcesRepository.getSources(category.id)
                progressVisible.value = false
                sourcesLiveData.value = result

//                progressVisible.value = true
//                val sourcesResponse: AllResponse =
//                    API_Manager.getApis().getSources(Constants.apiKey, category = category.id)
//                progressVisible.value = false
//                sourcesLiveData.value = sourcesResponse.sources
            } catch (ex: Exception) {
                progressVisible.value = false
                messageLiveData.value = ex.localizedMessage
            }

        }

//        API_Manager.getApis().getSources(Constants.apiKey, category.id)
//            .enqueue(object : Callback<AllResponse> {
//                override fun onFailure(call: Call<AllResponse>, exception: Throwable) {
//                    Log.e("data", exception.localizedMessage)
//                    // Long.e("data",t.localizedMessage)
//                    progressVisible.value = false
//                    messageLiveData.value = exception.localizedMessage
//                }
//
//                override fun onResponse(call: Call<AllResponse>, response: Response<AllResponse>) {
//                   // prgoreesBar.isVisible = false
//                    // Log.e("data",response.body().toString())
//                    //addSourcesToTabellayout(response.body()?.sources)
//                    progressVisible.value=false
//                    sourcesLiveData.value = response.body()?.sources
//                    }
//            })

    }

     fun getNewsBySources(sources: SourcesItem) {
        viewModelScope.launch {
            progressVisible.value=true
            try {
                // use repository
                progressVisible.value = true
                val result = newsRepository.getNews(sources.id)
                progressVisible.value = false
                newsLiveData.value = result

             //  val result =
                //     API_Manager.getApis().getNewsSources(Constants.apiKey, sources = sources.id ?: "")
                //newsLiveData.value = result.articles
                progressVisible.value=false
            }catch (ex:Exception){
                messageLiveData.value = ex.localizedMessage
                progressVisible.value=false
            }

        }
    //        API_Manager.getApis().getNewsSources(Constants.apiKey, sources.id ?: "")
//            .enqueue(object : Callback<ResponseBBCnews> {
//                override fun onResponse(
//                    call: Call<ResponseBBCnews>,
//                    response: Response<ResponseBBCnews>
//                ) {
//                    newsLiveData.value = response.body()?.articles
//                    progressVisible.value = false
//                    newsLiveData.value = response.body()?.articles
//                    //response.body().re
//                    //  adapter.ChangeData(response.body()?.articles)
//                }
//
//                override fun onFailure(call: Call<ResponseBBCnews>, t: Throwable) {
//                    progressVisible.value = false
//                    messageLiveData.value = t.localizedMessage
//                }
//
//            })
    }


}