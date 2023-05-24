package com.example.photocardapp.views

import PhotocardsViewModel
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.text.set
import com.example.photocardapp.R
import com.example.photocardapp.databinding.FragmentEditNoteBinding
import com.example.photocardapp.models.PhotocardModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class EditNoteFragment : Fragment() {

    var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photocard = arguments?.getParcelable<PhotocardModel>("photocard")
        if (photocard != null) {
            binding.apply {
                editTextNote.setText(photocard.notes)
//                buttonSaveNote.setOnClickListener(
//                )
//                    // WIP
            }
        }
    }
}