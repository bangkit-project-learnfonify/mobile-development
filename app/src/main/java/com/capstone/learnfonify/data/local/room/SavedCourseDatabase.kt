package com.capstone.learnfonify.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capstone.learnfonify.data.local.entity.SavedCourseEntity


@Database(
    entities = [SavedCourseEntity::class],
    version = 1
)

abstract class SavedCourseDatabase: RoomDatabase() {
    companion object{
        var INSTANCE : SavedCourseDatabase? = null

        fun getDatabase(context: Context): SavedCourseDatabase?{
            if (INSTANCE==null){
                synchronized(SavedCourseDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, SavedCourseDatabase::class.java, "saved_database")
                        .build()

                }
            }
            return INSTANCE
        }
    }
    abstract fun savedCourseDao(): SavedCourseDao

}