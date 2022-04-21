package events.ui.news

import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import events.api.API_Manager
import events.model.AllResponse
import events.model.ArticlesItem
import events.model.ResponseBBCnews
import events.model.SourcesItem
import events.ui.Constants
import events.ui.categories.Categories
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class NewsViewModel : ViewModel() {
    //...to change data
    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()
    val progressVisible = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()

    //... coroutines
    fun getNewsSources(category: Categories) {
        // used coroutines
        viewModelScope.launch {
            try {
                progressVisible.value = true
                val sourcesResponse: AllResponse =
                    API_Manager.getApis().getSources(Constants.apiKey, category = category.id)
                progressVisible.value = false
                sourcesLiveData.value = sourcesResponse.sources
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
                val result =
                    API_Manager.getApis().getNewsSources(Constants.apiKey, sources = sources.id ?: "")
                newsLiveData.value = result.articles
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