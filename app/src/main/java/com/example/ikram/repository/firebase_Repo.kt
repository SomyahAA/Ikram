package com.example.ikram_data.repository

import com.example.ikram_data.Database.pictureDataClass
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class firebase_Repo {

    fun uploadPicturesStorage(imageName:String,title:String,description:String, donationCategory:String, location:String) = FirebaseStorage.getInstance().getReference(imageName)

    fun getPhotosFormFireStore()=  FirebaseFirestore.getInstance().collection("Pictures uploaded")

    val picturesCollection = Firebase.firestore.collection("Pictures uploaded")

    fun uploadPicturesFireStore() = picturesCollection

    fun deletePicturesFireStore(picture: pictureDataClass) = picturesCollection
        .whereEqualTo("imageName",picture.imageName)




}