package com.example.ecomerce.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import com.bumptech.glide.Glide
import com.example.ecomerce.Adapters.CategoryAdapter
import com.example.ecomerce.Adapters.ProductAdapter
import com.example.ecomerce.Models.AddProductModel
import com.example.ecomerce.Models.CategoryModel
import com.example.ecomerce.R
import com.example.ecomerce.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        getCategories()
        getProducts()
        getSliderImg()
        return binding.root


    }

    private fun getSliderImg() {
        Firebase.firestore.collection("slider").document("item").get().addOnSuccessListener {
            Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImg)
        }
    }

    private fun getProducts() {
        var list=ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.productRv.adapter=ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategories() {
        var list=ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryrv.adapter=CategoryAdapter(requireContext(),list)
            }
    }

}