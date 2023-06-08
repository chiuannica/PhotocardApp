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
    private var yutaImage: String = "https://64.media.tumblr.com/735c8b2923a89acf09b3ee07cb930e89/1852844f89a4b3f1-47/s540x810/2876c600a05e5f0a4e0861d32db086a782b798d4.png"
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