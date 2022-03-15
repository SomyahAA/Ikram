package com.example.ikram.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikram_data.Database.pictureDataClass
import com.example.ikram_data.repository.firebase_Repo

private const val TAG = "deleteViewModel"
class deleteViewModel:ViewModel() {

    private val donationRepository = firebase_Repo()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    fun deleteAnImage(bookOfCovidDataClassPhotos: pictureDataClass) {
        var imageSnapShot =
            donationRepository.deletePicturesFireStore(bookOfCovidDataClassPhotos).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        donationRepository.picturesCollection.document(document.id).delete()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
    }


}