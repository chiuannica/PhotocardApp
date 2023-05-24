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
    private var yutaImage: String = "https://kpopping.com/documents/0d/0/social-widget/Yuta-facePicture(4).webp?v=de2b5"

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
                .override(500, 800)
                .centerCrop()
                .into(imageViewFirst)
        }
    }
}