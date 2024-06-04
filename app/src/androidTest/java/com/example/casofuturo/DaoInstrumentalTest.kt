package com.example.casofuturo

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.casofuturo.Modelo.local.CentroFuturoDao
import com.example.casofuturo.Modelo.local.database.CourseDataBase
import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DaoInstrumentalTest {

    private lateinit var centroFuturoDao: CentroFuturoDao
    private lateinit var db:CourseDataBase
    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
         db = Room.inMemoryDatabaseBuilder(context,CourseDataBase::class.java).build()
        centroFuturoDao = db.getCentroFuturoDao()
    }
    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertCoursesList() = runBlocking {
      val coursesEntity= listOf(
          CoursesEntity("43","prueba","test1","url",4,"junio"),
          CoursesEntity("45","prueba2","test2","url",4,"junio")

      )
             centroFuturoDao.insertAllCourses(coursesEntity)

        val coursesLivedata = centroFuturoDao.getAllCourses()
        val coursesList :List<CoursesEntity> = coursesLivedata.value?: emptyList()


        // verificamos el listao

        assertThat(coursesList,not(emptyList()))
        assertThat(coursesList.size,equalTo(2))

    }

// implemente test para testear la into

}