package com.capstone.learnfonify.data.response

import com.google.gson.annotations.SerializedName

data class UserFromId(

	@field:SerializedName("data")
	val data: List<UserFromIdItem>,

	@field:SerializedName("statusText")
	val statusText: String? = null,

	@field:SerializedName("count")
	val count: Any? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("status")
	val status: Int? = null
)

data class UserFromIdItem(

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("interest")
	val interest: Any? = null,

	@field:SerializedName("majoring")
	val majoring: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("semester")
	val semester: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)
