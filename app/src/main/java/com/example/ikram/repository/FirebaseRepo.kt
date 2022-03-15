package com.example.ikram.repository

import com.example.ikram.database.PictureDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

class FirebaseRepo {

    fun uploadPicturesStorage(
        imageName: String,
        title: String,
        description: String,
        donationCategory: String,
        location: String
    ) = FirebaseStorage.getInstance().getReference(imageName)

    fun getPhotosFormFireStore() = FirebaseFirestore.getInstance().collection("Pictures uploaded")

    private val picturesCollection = Firebase.firestore.collection("Pictures uploaded")

    fun uploadPicturesFireStore() = picturesCollection

    fun deletePicturesFireStore(picture: PictureDataClass) = picturesCollection
        .whereEqualTo("imageName", picture.imageName)

}