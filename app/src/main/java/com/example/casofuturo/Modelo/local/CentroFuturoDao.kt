package com.example.casofuturo.Modelo.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.casofuturo.Modelo.local.entities.CoursesDetailEntity
import com.example.casofuturo.Modelo.local.entities.CoursesEntity



@Dao
interface CentroFuturoDao {


    // insertar lista de Courses
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCourses(listCourses: List<CoursesEntity>)


    // seleccionar Listado de Courses

    @Query("SELECT * FROM courses_list_table ORDER BY id ASC")
    fun getAllCourses(): LiveData<List<CoursesEntity>>

    // inserta de a 1 course
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseDetail(course: CoursesDetailEntity)

    @Query("SELECT * FROM course_detail_table WHERE id=:id")
    fun getCourseDetailById(id: String): LiveData<CoursesDetailEntity>


}