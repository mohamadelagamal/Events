package com.example.data.model

import com.example.domain.model.AllResponseDTO
import com.example.domain.model.ArticlesItemDTO
import com.example.domain.model.ResponseBBCnewsDTO
import com.example.domain.model.SourcesItemDTO
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

fun <T> Any.convertTo(clazz: Class<T>):T{
	val json = Gson().toJson(this)
	return Gson().fromJson(json,clazz)
}
data class ResponseBBCnews(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
){
//	fun toNewsResponseDTO():ResponseBBCnewsDTO{
//		val json=Gson().toJson(this)
//		return Gson().fromJson(json,ResponseBBCnewsDTO::class.java)
//	}
}
data class ArticlesItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
){
//	fun toArticlesItemsDTO():ArticlesItemDTO{
//		val json =Gson().toJson(this)
//		return Gson().fromJson(json,ArticlesItemDTO::class.java)
//	}
}

data class Source(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
