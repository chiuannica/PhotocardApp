package com.example.photocardapp.views

import PhotocardsViewModel
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.photocardapp.databinding.FragmentEditNoteBinding
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.repository.SharedPreferencesRepository

@SuppressLint("NewApi")
class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var photocard: PhotocardModel
    private lateinit var viewModel: PhotocardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = PhotocardsViewModel(SharedPreferencesRepository(requireContext()))
        photocard = arguments?.getParcelable<PhotocardModel>("photocard")!!

        if (photocard != null) {
            binding.apply {
                editTextNote.setText(photocard.notes)

                buttonSaveNote.setOnClickListener {
                    saveNote()
                }
            }
        }
    }

    fun saveNote() {
        val newNotes = binding.editTextNote.text.toString()
        photocard.notes = newNotes

        viewModel.saveNotesPhotocard(photocard)

        println("New note: $newNotes")
        Toast.makeText(requireContext(), "Saved Note", Toast.LENGTH_SHORT).show()
    }

}