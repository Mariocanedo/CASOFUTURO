package com.example.casofuturo.Modelo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_detail_table")
data class CoursesDetailEntity(

    @PrimaryKey
    val id: String,
    val title: String,
    val previewDescription: String,
    val image: String,
    val weeks: Int,
    val tuition: String,
    val miniumSkill : String,
     val scholarshipAvailable :Boolean,
    val modality :String,
    val start: String

)