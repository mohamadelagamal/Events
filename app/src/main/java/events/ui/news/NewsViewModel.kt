package events.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.SourcesItemDTO
import com.example.domain.repos.NewsRepository
import com.example.domain.repos.SourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import events.ui.categories.Categories
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var newsRepository: NewsRepository,
    var sourcesRepository: SourceRepository
): ViewModel() {
    //...to change data
    val sourcesLiveData = MutableLiveData<List<SourcesItemDTO?>?>()
    val newsLiveData = MutableLiveData<List<ArticlesItemDTO?>?>()
    val progressVisible = MutableLiveData<Boolean>()
    val messageLiveData = MutableLiveData<String>()

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
            } catch (ex: Exception) {
                progressVisible.value = false
                messageLiveData.value = ex.localizedMessage
            }

        }

    }

     fun getNewsBySources(sources: SourcesItemDTO) {
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

    }


}