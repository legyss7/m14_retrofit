package com.hw14

import android.os.Build
import androidx.fragment.app.viewModels
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.annotation.RequiresApi

import coil.load

import com.hw14.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val viewModel: MainViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUserData()

        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            userData?.let {
                val data = it.results.first()
                binding.img.load(data.picture.medium)
                binding.firstName.text = data.name.first
                binding.lastName.text = data.name.last
                binding.phone.text = data.phone
            }
        }

        binding.button.setOnClickListener {
            viewModel.fetchUserData()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}