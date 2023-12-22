package com.capstone.learnfonify.data.response

import com.google.gson.annotations.SerializedName

data class ListCategory(

	@field:SerializedName("data")
	val data: List<CategoryItem> ,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("count")
	val count: Any? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class CategoryItem(

	@field:SerializedName("category")
	val category: String? = null
)
