package com.capstone.learnfonify.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "saved_course")
data class SavedCourseEntity(
    @PrimaryKey()
    @ColumnInfo(name = "course_id") val courseId: Int,
    @ColumnInfo(name = "title_course") val titleCourse: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "img_url") val imgUrl: String,

    ) : Parcelable