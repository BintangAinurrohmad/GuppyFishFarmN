package com.example.guppyfishfarm.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guppyfishfarm.application.FarmApp
import com.example.guppyfishfarm.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var applicationContext: Context
    private val farmViewModel: FarmViewModel by viewModels {
        FarmViewModelFactory((applicationContext as FarmApp).repository)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FarmListAdapter { farm ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(farm)
            findNavController().navigate(action)
        }

        binding.dataRecyclerView.adapter = adapter
        binding.dataRecyclerView.layoutManager = LinearLayoutManager(context)
        farmViewModel.allFarms.observe(viewLifecycleOwner) { farms ->
            farms.let {
                if (farms.isEmpty()) {
                    binding.notFound.visibility = View.VISIBLE
                    binding.ilustrationImage.visibility = View.VISIBLE
                }else {
                    binding.notFound.visibility = View.GONE
                    binding.ilustrationImage.visibility = View.GONE
                }
                adapter.submitList(farms)
            }
        }

        binding.addButt.setOnClickListener {
//            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(null)
//            findNavController().navigate(action)
       }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}