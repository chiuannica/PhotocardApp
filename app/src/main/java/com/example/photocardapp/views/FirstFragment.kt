package com.example.photocardapp.views

import PhotocardsViewModel
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.photocardapp.R
import com.example.photocardapp.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private var yutaImage: String = "https://64.media.tumblr.com/4c413b46771e190a4c00cb4b51f7812e/tumblr_inline_po3so5cDRu1w1pmlp_1280.jpg"

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

        binding.apply {
            buttonFirst.setOnClickListener {
                findNavController().navigate(R.id.action_global_PhotocardsFragment)
            }
            buttonChoose.setOnClickListener {
                findNavController().navigate(R.id.action_global_ChooseFragment)
            }
            Glide.with(requireContext())
                .load(yutaImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageViewFirst)
        }
    }
}