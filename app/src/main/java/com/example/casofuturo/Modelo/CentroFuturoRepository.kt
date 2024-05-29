package com.example.casofuturo.Modelo


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.casofuturo.Modelo.local.CentroFuturoDao
import com.example.casofuturo.Modelo.local.entities.CoursesDetailEntity
import com.example.casofuturo.Modelo.remote.CentroFuturoApi
import com.example.casofuturo.Modelo.remote.RetrofitClient
import com.example.casofuturo.Modelo.remote.fromInternet.CourseDetail

import retrofit2.Retrofit







class CentroFuturoRepository( private val centroFuturoDao: CentroFuturoDao) {


    // retrofit Cliente

    private val networkService = RetrofitClient.retrofitInstance()

    // dao listado
    val coursesListLiveDataRepository = centroFuturoDao.getAllCourses()

    // un elemento

    val courseDetailLiveData= MutableLiveData<CoursesDetailEntity>()


    suspend fun fetchCourse() {
        val service = kotlin.runCatching { networkService.fethCourseList() }
        service.onSuccess {
            when (it.code()) {
                in 200..299 -> it.body()?.let {
                    Log.d("cursos", it.toString())
                    centroFuturoDao.insertAllCourses(fromInternetCoursesEntity(it))
                }

                else -> Log.d("Repo", "${it.code()}-${it.errorBody()}")
            }
            service.onFailure {
                Log.e("Error", "${it.message}")
            }
        }
    }



  suspend fun fetchCourseDetail(id:String): CoursesDetailEntity?{
      val service = kotlin.runCatching { networkService.fetchCourseDetail(id) }
      return service.getOrNull()?.body()?.let {courseDetail ->

          val coursesDetailEntity = fromInternetCourseDetailEntity(courseDetail)
          centroFuturoDao.insertCourseDetail(coursesDetailEntity)
          coursesDetailEntity
      }


  }














            }



