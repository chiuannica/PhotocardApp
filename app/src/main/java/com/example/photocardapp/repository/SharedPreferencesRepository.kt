package com.example.photocardapp.repository

import android.content.Context
import com.example.photocardapp.models.PhotocardModel
import com.example.photocardapp.views.PhotocardsFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

const val SHARED_PREF_NAME = "MyPhotocards"

class SharedPreferencesRepository(private val context: Context) {
    private val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()
    private val gson = Gson()

    fun savePhotocards(photocards: List<PhotocardModel>) {
        val photocardsJson = gson.toJson(photocards)
        editor.putString(SHARED_PREF_NAME, photocardsJson)
        editor.apply()
    }

    fun saveNotesPhotocard(newPhotocard: PhotocardModel, photocards: List<PhotocardModel>) {
        for (index in 0 until photocards.size) {
            if (photocards[index].id == newPhotocard.id) {
                photocards[index].notes = newPhotocard.notes
            }
        }
        savePhotocards(photocards)
    }

    fun loadPhotocards(): List<PhotocardModel>? {
        val photocardsJson = sharedPrefs.getString(SHARED_PREF_NAME, null)
        return if (photocardsJson != null) {
            val listType = object : TypeToken<List<PhotocardModel>>() {}.type
            gson.fromJson(photocardsJson, listType)
        } else {
            null
        }
    }
}