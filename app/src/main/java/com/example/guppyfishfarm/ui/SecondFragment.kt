package com.example.guppyfishfarm.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guppyfishfarm.R
import com.example.guppyfishfarm.application.FarmApp
import com.example.guppyfishfarm.databinding.FragmentSecondBinding
import com.example.guppyfishfarm.model.farm

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val farmViewModel: FarmViewModel by viewModels {
        FarmViewModelFactory((applicationContext as FarmApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var farm: farm? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        farm = args.farm
        if (farm != null) {
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = "Ubah"
            binding.nameText.setText(farm?.name)
            binding.addressText.setText(farm?.address)
            binding.speciesText.setText(farm?.species)
        }
        val name = binding.nameText.text
        val address = binding.addressText.text
        val species = binding.speciesText.text
        binding.saveButton.setOnClickListener {
            if (name.isEmpty()) {
                Toast.makeText(context, "Nama Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else if (address.isEmpty()) {
                Toast.makeText(context, "Alamat Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
             }else if (species.isEmpty()) {
                Toast.makeText(context, "Jenis Tidak Boleh Kosong", Toast.LENGTH_SHORT).show()
            } else {
                if ( farm == null) {
                    val farm = farm(0, name.toString(), address.toString(), species.toString())
                    farmViewModel.insert(farm)
                }else {
                    val farm = farm(farm?.id!!, name.toString(), address.toString(), species.toString())
                    farmViewModel.update(farm)
                }

                findNavController().popBackStack() //dismis halaman
            }
           // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.deleteButton.setOnClickListener {
            farm?.let { farmViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}