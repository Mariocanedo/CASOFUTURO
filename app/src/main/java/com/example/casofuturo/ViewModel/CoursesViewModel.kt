package com.example.casofuturo.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.casofuturo.Modelo.CentroFuturoRepository
import com.example.casofuturo.Modelo.local.database.CourseDataBase
import com.example.casofuturo.Modelo.local.entities.CoursesDetailEntity
import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import kotlinx.coroutines.launch

class CoursesViewModel (application: Application):AndroidViewModel(application){


  // conexion repository
    private val repository : CentroFuturoRepository
    // entidad
    private val courseDetailLiveData = MutableLiveData<CoursesDetailEntity>()
    // para seleccionar
    private var isSelected : String="-1"


      init{
               val bd= CourseDataBase.getDataBase(application)
               val centroFuturoDao = bd.getCentroFuturoDao()
               repository= CentroFuturoRepository(centroFuturoDao)

              //LAMAR A FECHTCOURSE

              viewModelScope.launch {
                repository.fetchCourse()
              }

         }

  // listado de los elementos
  fun getCoursesList():LiveData<List<CoursesEntity>> = repository.coursesListLiveDataRepository


  // para obtener el detalle de los cursos
  fun getCourseDetail() : LiveData<CoursesDetailEntity> = courseDetailLiveData


  fun getCourseDetailByIdFromInternet(id:String) = viewModelScope.launch {

    val courseDetail = repository.fetchCourseDetail(id)
    courseDetail?.let {
      courseDetailLiveData.postValue(it)
    }


  }




}