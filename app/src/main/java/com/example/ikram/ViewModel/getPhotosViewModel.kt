package com.example.ikram.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ikram_data.Database.pictureDataClass
import com.example.ikram_data.repository.firebase_Repo
import com.google.firebase.firestore.ktx.toObject

private const val TAG = "getPhotosViewModel"
class getPhotosViewModel:ViewModel() {

    private val donationRepository = firebase_Repo()
    val userLiveDataError = MutableLiveData<String>()
    val userLiveDataSuccessful = MutableLiveData<String>()
    val uriLiveDataForPhotos = MutableLiveData<List<pictureDataClass>>()
    val uriLiveDataForPhotosDetail = MutableLiveData<pictureDataClass>()


    /**
     * The function below job is to get the photos from the firebase
     */
    fun getBookOfCovidPhotos(){
        var photoList = mutableListOf<pictureDataClass>()
        var imageSnapShot = donationRepository.getPhotosFormFireStore().get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    var x = document.toObject<pictureDataClass>()
                    x.elementId
                    photoList.add(x)

                    Log.d(TAG, "${document.id} => ${document.data}")


                }

                uriLiveDataForPhotos.postValue(photoList)


            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}