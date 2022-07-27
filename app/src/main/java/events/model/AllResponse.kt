package events.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class AllResponse(

	@field:SerializedName("sources")
	var sources: List<SourcesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	// to now error
	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
@Entity
data class SourcesItem(
	@ColumnInfo
	@field:SerializedName("country")
	val country: String? = null,
	@ColumnInfo
	@field:SerializedName("name")
	val name: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@ColumnInfo
	@field:SerializedName("language")
	val language: String? = null,

	@ColumnInfo
	@PrimaryKey
	@field:SerializedName("id")
	val id: String ,

	@ColumnInfo
	@field:SerializedName("category")
	val category: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null
)
