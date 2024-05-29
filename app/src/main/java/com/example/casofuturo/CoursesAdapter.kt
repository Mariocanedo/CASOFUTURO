package com.example.casofuturo

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.casofuturo.Modelo.local.entities.CoursesEntity
import com.example.casofuturo.databinding.CoursesListBinding

class CoursesAdapter: RecyclerView.Adapter<CoursesAdapter.CourseVH>() {

    // referencia para el Adapter

    private var listCourses = listOf<CoursesEntity>()
    private val selectedCourse= MutableLiveData<CoursesEntity>()

    fun update (list:List<CoursesEntity>){
        listCourses= list
        notifyDataSetChanged()
    }

    // FUNCION SELECCIONAR

    fun selectCourse():LiveData<CoursesEntity> = selectedCourse

    inner class CourseVH(private val mbinding: CoursesListBinding) :
        RecyclerView.ViewHolder(mbinding.root), View.OnClickListener {

        fun bin(course: CoursesEntity) {
            Glide.with(mbinding.ivLogo).load(course.image).centerCrop().into(mbinding.ivLogo)
            mbinding.tvname.text = course.title
            mbinding.tvdescription.text = course.previewDescription
            mbinding.tvduration.text = "duracion:" + course.weeks.toString() + "semanas"
            mbinding.tvstart.text = "Inicio" + course.start
            itemView.setOnClickListener(this)

        }

        @SuppressLint("SuspiciousIndentation")
        override fun onClick(v: View?) {
        selectedCourse.value = listCourses[adapterPosition]
            Log.d("ONCLICK", adapterPosition.toString())
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseVH {
        return  CourseVH(CoursesListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount()= listCourses.size

    override fun onBindViewHolder(holder: CourseVH, position: Int) {
        val course = listCourses[position]
        holder.bin(course)
    }


}