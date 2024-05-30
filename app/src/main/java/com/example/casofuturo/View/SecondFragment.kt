package com.example.casofuturo.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.casofuturo.R
import com.example.casofuturo.ViewModel.CoursesViewModel
import com.example.casofuturo.databinding.FragmentSecondBinding
import kotlin.math.min

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
            var id = it.id
            var name = it.title
            // cargamos datos desde la seleccion

            Glide.with(binding.ivLogo).load(it.image).into(binding.ivLogo)
            binding.tvTitle.text = it.title
            binding.tvDescription.text= it.previewDescription
            binding.tvMinimumSkill.text = "Conocimiento minimo : ${it.miniumSkill}"
            binding.tvModality.text = "Modalidad : ${it.modality}"
            binding.tvTuition.text = "Valor del curso : ${it.tuition}"
            binding.tvDuration.text = "Duracion del curso : ${it.weeks} "
            binding.tvStart.text ="Inicio de clases : ${it.start}"


            // ACTION SEND EMAIL
          binding.btMail.setOnClickListener {

              Log.d("BUTTON", "Click")

              val mintent = Intent(Intent.ACTION_SEND)

              mintent.data = Uri.parse("mailto")
              mintent.type = "text/plain"


          // DIRECCION DE CORREO
              mintent.putExtra(Intent.EXTRA_EMAIL, arrayOf("admisión@centrofuturo.cl"))

              // ASUNTO
              mintent.putExtra(Intent.EXTRA_SUBJECT,

                  "SOLICITO INFORMACIÓN SOBRE ESTE CURSO" +id

                  )

              mintent.putExtra(

                  Intent.EXTRA_TEXT, "HOLA\n" +

                          "Quisiera pedir información sobre este curso " + name + " ,\n" +
                          "me gustaría que me contactaran a este correo o al siguiente número\n" +
                          " _________\n" +
                          "Quedo atento."

              )

              try {

                  startActivity(mintent)

              } catch (e: Exception) {

                  Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
              }

          }
        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}