package com.example.domain.model

data class ResponseBBCnewsDTO(

	val totalResults: Int? = null,
	val articles: List<ArticlesItemDTO?>? = null,
	val status: String? = null
)

data class ArticlesItemDTO(

	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: String? = null,
	val description: String? = null,
	val source: SourcesItemDTO? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null
)

data class SourcDTO(

	val name: String? = null,

	val id: String? = null
)
