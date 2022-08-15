package com.example.domain.model

data class AllResponseDTO(
	var sources: List<SourcesItemDTO?>? = null,
	val status: String? = null,
	// to now error
	val code: String? = null,
	val message: String? = null
)
data class SourcesItemDTO(
	val country: String? = null,
	val name: String? = null,
	val description: String? = null,
	val language: String? = null,
	val id: String ,
	val category: String? = null,
	val url: String? = null
)
