package com.capstone.learnfonify.data.response

import com.google.gson.annotations.SerializedName

data class CourseFromCategory(

    @field:SerializedName("data")
    val data: List<CourseItem>,

    @field:SerializedName("statusText")
    val statusText: String? = null,

    @field:SerializedName("count")
    val count: Any? = null,

    @field:SerializedName("error")
    val error: Any? = null,

    @field:SerializedName("status")
    val status: Int? = null
)

data class CourseItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("instructor")
    val instructor: Any? = null,

    @field:SerializedName("level")
    val level: String? = null,

    @field:SerializedName("organizer")
    val organizer: String? = null,

    @field:SerializedName("fee")
    val fee: Any? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("rating")
    val rating: Any? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("category")
    val category: String? = null
)
