package com.example.ecomerce.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomerce.Adapters.CategoryProductAdapter
import com.example.ecomerce.Adapters.ProductAdapter
import com.example.ecomerce.Models.AddProductModel
import com.example.ecomerce.R
import com.example.ecomerce.databinding.ActivityCategoryBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        getProducts(intent.getStringExtra("cate"))
    }

    private fun getProducts(categoryName: String?) {
        var list=ArrayList<AddProductModel>()
        Firebase.firestore.collection("products").whereEqualTo("productCategory",categoryName)
            .get().addOnSuccessListener {
                list.clear()
                for (doc in it.documents){
                    val data =doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                val recyclerView=findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter= CategoryProductAdapter(this,list)
            }
    }
}