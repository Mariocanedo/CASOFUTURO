package com.example.casofuturo

import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TestCoursesEntity {

    private lateinit var coursesEntity: CoursesEntity

     @Before
     fun setup(){
         coursesEntity = CoursesEntity(
             id="1",
             title = "Prueba Android",
             previewDescription = " Prueba test",
             image = "image",
             weeks = 2,
             start = "Mayo 2024"
         )

     }

     @After

     fun tearDown(){
         // acciones de limpieza de liberacion de recursos
     }

     @Test
    fun testCourseConstructor(){
        // verificar los valores asignados

         assert(coursesEntity.id=="1")
         assert(coursesEntity.title == "Prueba Android")
         assert(coursesEntity.previewDescription==" Prueba test")
         assert(coursesEntity.image== "image" )
         assert(coursesEntity.weeks ==2)
         assert(coursesEntity.start =="Mayo 2024")

    }

}