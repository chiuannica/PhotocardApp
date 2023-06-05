package com.example.photocardapp.views

import PhotocardFragment
import PhotocardItemClickListener
import PhotocardsViewModel
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photocardapp.R
import com.example.photocardapp.adapters.PhotocardsAdapter
import com.example.photocardapp.databinding.PhotocardsBinding
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.repository.SharedPreferencesRepository

@SuppressLint("NewApi")
class PhotocardsFragment() : Fragment(), PhotocardItemClickListener {
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var adapter: PhotocardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PhotocardsBinding.inflate(inflater, container, false)
        setupRecyclerView(binding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        viewModel = PhotocardsViewModel(sharedPreferencesRepository)

//        viewModel.deleteAllPhotocards()
//        viewModel.giveAllPhotocards()
//        viewModel.savePhotocards()

        viewModel.loadPhotocards()
        observePhotocards()
    }

    private fun observePhotocards() {
        viewModel.photocardsLiveData.observe(viewLifecycleOwner) { photocards ->
            adapter.submitList(photocards)
        }
    }

    private fun setupRecyclerView(binding: PhotocardsBinding) {
        adapter = PhotocardsAdapter()
        adapter.setOnItemClickListener(this)
        binding.photocardsRecyclerView.adapter = adapter
        binding.photocardsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun onItemClick(photocard: PhotocardModel) {
        val fragment = PhotocardFragment()
        fragment.arguments = Bundle().apply {
            putParcelable("photocard", photocard)
        }
        parentFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, fragment)
            .addToBackStack(null)
            .commit()
    }
}