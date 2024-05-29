package com.example.casofuturo.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.casofuturo.R
import com.example.casofuturo.ViewModel.CoursesViewModel
import com.example.casofuturo.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val viewModel : CoursesViewModel by activityViewModels()
    private var courseId : String? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RECIBIENDO EL ID DESDE LA OTRA PANTALLA
        arguments?.let { bundle ->

            courseId = bundle.getString("courseId")
            Log.d("SELECCION", courseId.toString())
        }

        courseId?.let { id ->
            viewModel.getCourseDetailByIdFromInternet(id)

        }

        viewModel.getCourseDetail().observe(viewLifecycleOwner, Observer {
            Log.d("SELECCION3", courseId.toString())

            // cargamos datos desde la seleccion

            Glide.with(binding.ivLogo).load(it.image).into(binding.ivLogo)
            binding.tvTitle.text = it.title
            binding.tvDescription.text= it.previewDescription
            binding.tvMinimumSkill.text = "Conocimiento minimo : ${it.miniumSkill}"
            binding.tvModality.text = "Modalidad : ${it.modality}"
            binding.tvTuition.text = "Valor del curso : ${it.tuition}"
            binding.tvDuration.text = "Duracion del curso : ${it.weeks} "
            binding.tvStart.text ="Inicio de clases : ${it.start}"

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}