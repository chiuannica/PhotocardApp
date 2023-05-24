package com.example.photocardapp.views

import PhotocardsViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.photocardapp.databinding.FragmentChooseBinding
import com.example.photocardapp.repository.SharedPreferencesRepository

class ChooseFragment : Fragment() {
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var _binding: FragmentChooseBinding
    private val binding get() = _binding!!

    private var yutaImage: String = "https://kpopping.com/documents/0d/0/social-widget/Yuta-facePicture(4).webp?v=de2b5"
    private var yutaImage2: String = "https://i.pinimg.com/originals/3e/4b/d7/3e4bd73348d9665a956caff68c595764.jpg"
    private var correctCard: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseBinding.inflate(inflater, container, false)
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        viewModel = PhotocardsViewModel(sharedPreferencesRepository)
        correctCard = (1..3).random()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            Glide.with(root)
                .load(yutaImage)
                .override(300, 500)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewOption1)

            Glide.with(root)
                .load(yutaImage)
                .override(300, 500)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewOption2)

            Glide.with(root)
                .load(yutaImage)
                .override(300, 500)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewOption3)

            imageViewOption1.setOnClickListener {
                chooseCard(1)
            }
            imageViewOption2.setOnClickListener {
                chooseCard(2)
            }
            imageViewOption3.setOnClickListener {
                chooseCard(3)
            }
        }
    }

    private fun disableCardSelection() {
        binding.imageViewOption1.setOnClickListener(null)
        binding.imageViewOption2.setOnClickListener(null)
        binding.imageViewOption3.setOnClickListener(null)
    }

    fun chooseCard(chosenCard: Int) {
        if (chosenCard == correctCard) {
            // add card to the user's cards
            winCard()
        } else {
            // WRONG!
            lose()
        }
        disableCardSelection()
    }

    private fun winCard() {
        Glide.with(requireContext())
            .load(yutaImage)
            .into(binding.imageViewAward)
        binding.textViewAlert.text = "You won a new photocard!"
        viewModel.addNewPhotocard()
    }

    private fun lose() {
        Glide.with(requireContext())
            .load(yutaImage2)
            .into(binding.imageViewAward)
        binding.textViewAlert.text = "You didn't win :("
    }
}