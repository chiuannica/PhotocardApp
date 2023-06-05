package com.example.photocardapp.views

import PhotocardsViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.photocardapp.databinding.FragmentChooseBinding
import com.example.photocardapp.repository.SharedPreferencesRepository

@SuppressLint("NewApi")
class ChooseFragment : Fragment() {
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var _binding: FragmentChooseBinding
    private val binding get() = _binding!!

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
        val img = viewModel.getRandomPhotocard().imageUrl
        var cardWidth = 250
        var cardHeight = 400
        binding.apply {
            Glide.with(root)
                .load(img)
                .override(cardWidth, cardHeight)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewOption1)

            Glide.with(root)
                .load(img)
                .override(cardWidth, cardHeight)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageViewOption2)

            Glide.with(root)
                .load(img)
                .override(cardWidth, cardHeight)
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
        binding.apply {
            textViewChoose.visibility = View.INVISIBLE
            Glide.with(root)
                .clear(imageViewOption1)

            Glide.with(root)
                .clear(imageViewOption2)

            Glide.with(root)
                .clear(imageViewOption3)

            imageViewOption1.setOnClickListener(null)
            imageViewOption2.setOnClickListener(null)
            imageViewOption3.setOnClickListener(null)
        }
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
        val img = viewModel.getRandomPhotocard().imageUrl
        Glide.with(requireContext())
            .load(img)
            .into(binding.imageViewAward)
        binding.textViewAlert.text = "You won a new photocard!"
        viewModel.addNewPhotocard()
    }

    private fun lose() {
        val img = viewModel.getRandomPhotocard().imageUrl
        Glide.with(requireContext())
            .load(img)
            .into(binding.imageViewAward)
        binding.textViewAlert.text = "You didn't win :("
    }
}