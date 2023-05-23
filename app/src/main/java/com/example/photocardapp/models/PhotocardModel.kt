package com.example.photocardapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotocardModel(
    val id: Int,
    val idolName: String,
    val title: String,
    val dateReceived: String,
    val notes: String,
    val imageUrl: String
) : Parcelable