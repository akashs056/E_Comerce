package com.example.ecomerce.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.animations.Toss
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.ecomerce.Fragments.Cart
import com.example.ecomerce.Fragments.Home
import com.example.ecomerce.R
import com.example.ecomerce.databinding.ActivityProductDetailBinding
import com.example.ecomerce.roomDB.AppDatabase
import com.example.ecomerce.roomDB.ProductModel
import com.example.ecomerce.roomDB.productDao
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

                cartAction(id,it.getString("productName"),it.getString("productSp"),it.getString("productCoverImg"))

                binding.imageSlider.setImageList(slideList)
            }.addOnFailureListener{
                Toast.makeText(this,"An Error Occurred",Toast.LENGTH_SHORT).show()
            }
    }

    private fun cartAction(id: String, productName: String?, productSp: String?, productCoverImg: String?) {
        val dao=AppDatabase.getInstance(this).productDao()
        if (dao.isExist(id)!=null){
            binding.addtoCart.text="Go To Cart"
        }else{
            binding.addtoCart.text="Add To Cart"
        }

        binding.addtoCart.setOnClickListener {
            if (dao.isExist(id)!=null){
                openCart()
            }else{
                addToCart(dao,id,productName,productSp,productCoverImg)
            }
        }
    }

    private fun openCart() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("OPEN_CART", true)
        startActivity(intent)
        finish()
    }

    private fun addToCart(
        dao: productDao,
        id: String,
        productName: String?,
        productSp: String?,
        productCoverImg: String?
    ) {
        val data=ProductModel(id,productName!!,productCoverImg!!,productSp!!)
        lifecycleScope.launch(Dispatchers.IO){
            dao.insertProduct(data)
            binding.addtoCart.text="Go To Cart"
        }
    }
    interface CartActionListener {
        fun openCartFragment()
    }

}