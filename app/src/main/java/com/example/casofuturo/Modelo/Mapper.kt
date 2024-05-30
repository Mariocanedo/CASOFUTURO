package com.example.casofuturo.Modelo

import com.example.casofuturo.Modelo.local.entities.CoursesDetailEntity
import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import com.example.casofuturo.Modelo.remote.fromInternet.CourseDetail
import com.example.casofuturo.Modelo.remote.fromInternet.Courses


fun fromInternetCoursesEntity( coursesList: List<Courses>) :List<CoursesEntity>{
        return coursesList.map {
            CoursesEntity(
                id= it.id,
                title = it.title,
                previewDescription = it.previewDescription,
                image = it.image,
                weeks = it.weeks,
                start = it.start
            )


        }

}

fun fromInternetCourseDetailEntity( course: CourseDetail) :CoursesDetailEntity{
    return CoursesDetailEntity(

        id= course.id,
        title = course.title,
        previewDescription = course.previewDescription,
        image = course.image,
        weeks = course.weeks,
        tuition = course.tuition,
      // miniumSkill = course.miniumSkill?: "",
       miniumSkill = course.minimumSkill,
        scholarshipAvailable = true,
        modality = course.modality,
        start = course.start

    )




}



