package com.example.ecomerce.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.denzcoskun.imageslider.animations.Toss
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ecomerce.databinding.ActivityProductDetailBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getDetails(intent.getStringExtra("id"))
    }

    private fun getDetails(id: String?) {
        Firebase.firestore.collection("products")
            .document(id!!).get().addOnSuccessListener {
                val list=it.get("productImages") as ArrayList<*>
                binding.title.text=it.getString("productName")
                binding.price.text=it.getString("productSp")
                binding.description.text=it.getString("productDescription")
                val slideList=ArrayList<SlideModel>()
                for (data in list){
                    val imageUrl = data.toString()
                    slideList.add(SlideModel(imageUrl,ScaleTypes.CENTER_INSIDE))
                }
                binding.imageSlider.setImageList(slideList)
            }.addOnFailureListener{
                Toast.makeText(this,"An Error Occurred",Toast.LENGTH_SHORT).show()
            }
    }
}