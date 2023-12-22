package com.capstone.learnfonify.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedCourseDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addToSaved(course: SavedCourseEntity)

    @Query("SELECT * FROM saved_course")
    fun getSavedCourse(): Flow<List<SavedCourseEntity>>

    @Query("SELECT count(*) FROM saved_course WHERE course_id = :id")
    fun checkCourse(id: Int ) : Int

    @Query("DELETE FROM saved_course WHERE course_id =:id")
    fun removeCourse(id: Int)
}