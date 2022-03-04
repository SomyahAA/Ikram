package com.example.ikram.ViewModel

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikram_data.Database.pictureDataClass
import com.example.ikram_data.repository.firebase_Repo
import com.google.firebase.auth.FirebaseAuth

class donationViewModel:ViewModel() {

    private val donationRepository = firebase_Repo()

    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()

    fun uploadPictureStorage(
        uri: Uri,
        name: String,
        title: String,
        description: String,
        donationCategory: String,
        location: String
    ) {
        val reference = uri.lastPathSegment?.let {
            donationRepository.uploadPicturesStorage(
                name,
                title,
                description,
                donationCategory,
                location
            )
        }
        reference?.putFile(uri)?.addOnSuccessListener {
            userLiveDataSuccessful.postValue("Successfully uploaded")
        }?.addOnFailureListener {
            userLiveDataError.postValue("Failed to upload file")


        }
    }

    fun uploadPictureFireStore(
        uri: Uri,
        imageName: String,
        title: String,
        description: String,
        donationCategory: String,
        location: String
    ) {
        uri.lastPathSegment?.let {
            donationRepository.uploadPicturesFireStore()
                .add(
                    pictureDataClass(
                        imageName,
                        FirebaseAuth.getInstance().uid.toString(),
                        title,
                        description,
                        donationCategory,
                        location
                    )
                )
        }
            ?.addOnSuccessListener {
                uploadPictureStorage(uri, imageName, title, description, donationCategory, location)
                userLiveDataSuccessful.postValue("Successfully uploaded")


            }?.addOnFailureListener {
                userLiveDataError.postValue("Failed to upload file")

            }
    }
}