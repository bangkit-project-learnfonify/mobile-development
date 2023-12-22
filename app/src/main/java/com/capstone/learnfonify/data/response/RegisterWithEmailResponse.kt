package com.capstone.learnfonify.data.response

import com.google.gson.annotations.SerializedName

data class RegisterWithEmailResponse(

	@field:SerializedName("data")
	val data: Any? = null,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("count")
	val count: Any? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
