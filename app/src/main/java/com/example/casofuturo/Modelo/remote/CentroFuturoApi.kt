package com.example.casofuturo.Modelo.remote

import com.example.casofuturo.Modelo.remote.fromInternet.CourseDetail
import com.example.casofuturo.Modelo.remote.fromInternet.Courses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CentroFuturoApi {


    @GET("courses")
    suspend fun fethCourseList(): Response<List<Courses>>


  @GET("courses/{id}")
   suspend fun fetchCourseDetail(@Path("id")id:String): Response<CourseDetail>


}