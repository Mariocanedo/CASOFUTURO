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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.casofuturo.CoursesAdapter
import com.example.casofuturo.R
import com.example.casofuturo.ViewModel.CoursesViewModel
import com.example.casofuturo.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel : CoursesViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         //DEBEMOS ISNTANCIAR EL ADAPTER
         val adapter = CoursesAdapter()
        binding.recyclerView.adapter= adapter
        binding.recyclerView.layoutManager= LinearLayoutManager(context)

        viewModel.getCoursesList().observe(viewLifecycleOwner, Observer {

            it?.let {

                Log.d("Listado", it.toString())
                adapter.update(it)
            }

        })



        //METODO PARA SELECCIONAR

        adapter.selectCourse().observe(viewLifecycleOwner, Observer {
       it.let {
           Log.d("SELECCION", it.toString())
       }
            val bundle= Bundle().apply {
                putString("courseId",it.id)
            }
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
        })










    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}