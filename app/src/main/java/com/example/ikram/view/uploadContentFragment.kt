package com.example.ikram.view

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.ikram.R
import com.example.ikram.ViewModel.donationViewModel
import com.example.ikram.databinding.FragmentUploadContentBinding
import java.util.*


class uploadContentFragment : Fragment() {
    private val uploadContentViewModel: donationViewModel by activityViewModels()

    private lateinit var  sharedPref: SharedPreferences
    var uri: Uri? = null


    var picRequestCode:Int = 6
    lateinit var binding: FragmentUploadContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUploadContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


    /**
     * The function below is to select an image from the user phone
     */
    private fun selectPic() {

        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent,picRequestCode)


    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        binding.saveTitleDecripButton.setOnClickListener {
            if (requestCode==picRequestCode&&resultCode== Activity.RESULT_OK) {
                var title = binding.titleEditText.text.toString()
                var description = binding.decripEditText.text.toString()
                var Location = binding.LocationEditText .text.toString()
                var donationType = binding.donationTypeEditText .text.toString()
                uri = data?.data!!
                var date = Date()

                uploadContentViewModel.uploadPictureFireStore(uri!!,Math.random().toString(),title,description,donationType,Location)

            }else{


                Toast.makeText(context, "Nothing was selected", Toast.LENGTH_SHORT).show()
            }

        }

    }
}