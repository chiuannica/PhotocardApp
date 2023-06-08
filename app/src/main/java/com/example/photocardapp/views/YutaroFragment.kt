package com.example.photocardapp.views

import PhotocardsViewModel
import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.photocardapp.databinding.FragmentYutaroBinding
import com.example.photocardapp.repository.SharedPreferencesRepository
import okhttp3.Headers

@SuppressLint("NewApi")
class YutaroFragment : Fragment() {
    private lateinit var viewModel: PhotocardsViewModel
    private lateinit var _binding: FragmentYutaroBinding
    private val binding get() = _binding!!

    private var correctCard: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYutaroBinding.inflate(inflater, container, false)
        val sharedPreferencesRepository = SharedPreferencesRepository(requireContext())
        viewModel = PhotocardsViewModel(sharedPreferencesRepository)
        correctCard = (1..3).random()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reset()
    }

    fun chooseCard(chosenCard: Int) {
        binding.apply {
            val client = AsyncHttpClient()
            val params = RequestParams()
            params["limit"] = "5"
            params["page"] = "0"

            val textViewOptions = listOf(textViewOption1, textViewOption2, textViewOption3)

            for ((index, textViewOption) in textViewOptions.withIndex()) {
                client.get("https://www.affirmations.dev/", params, object : JsonHttpResponseHandler() {
                    override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                        val fortune = json.jsonObject.getString("affirmation")
                        textViewOption.text = fortune
                        textViewOption.setTypeface(null, Typeface.NORMAL)
                        if (chosenCard == index + 1) {
                            textViewOption.setTypeface(null, Typeface.BOLD)
                        }
                    }

                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        Log.e("Yutaro Fragment: ", errorResponse)
                    }
                })
            }
        }

        disableCardSelection()
        enableTextViews()
        enableAgain()
    }


    private fun disableCardSelection() {
        binding.apply {
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
    private fun reset() {
        enableCardSelection()
        disableAgain()
        enableTextViews()
        disableTextViews()
    }

    private fun disableTextViews() {
        binding.apply {
            textViewOption1.visibility = View.INVISIBLE
            textViewOption2.visibility = View.INVISIBLE
            textViewOption3.visibility = View.INVISIBLE
        }
    }

    private fun enableTextViews() {
        binding.apply {
            textViewOption1.visibility = View.VISIBLE
            textViewOption2.visibility = View.VISIBLE
            textViewOption3.visibility = View.VISIBLE
        }
    }

    private fun enableAgain() {
        binding.apply {
            yutaroAgainTextView.visibility = View.VISIBLE
            yutaroAgainButton.visibility = View.VISIBLE
            yutaroAgainButton.setOnClickListener {
                reset()
            }
        }
    }

    private fun disableAgain() {
        binding.apply {
            yutaroAgainTextView.visibility = View.INVISIBLE
            yutaroAgainButton.visibility = View.INVISIBLE
        }
    }

    private fun enableCardSelection() {
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



}