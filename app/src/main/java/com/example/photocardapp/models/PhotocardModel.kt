package com.example.photocardapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotocardModel(
    val id: Int,
    val idolName: String,
    val title: String,
    var dateReceived: String,
    var notes: String,
    val imageUrl: String
) : Parcelable