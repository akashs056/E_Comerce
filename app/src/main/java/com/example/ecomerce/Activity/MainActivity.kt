package com.example.ecomerce.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.ecomerce.Fragments.Cart
import com.example.ecomerce.Fragments.Home
import com.example.ecomerce.Fragments.MyOrders
import com.example.ecomerce.Fragments.Profile
import com.example.ecomerce.R
import com.example.ecomerce.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Objects

class MainActivity : AppCompatActivity(),ProductDetailActivity.CartActionListener {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.getBooleanExtra("OPEN_CART", false)) {
            openCartFragment()
        } else {
            val transaction : FragmentTransaction=supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container,Home())
            transaction.commit()

            val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottom_navigation)

            val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                val charSequence: CharSequence = Objects.requireNonNull(item.title).toString()
                when {
                    charSequence == "Home" -> transaction.replace(R.id.container, Home())
                    charSequence == "Profile" -> transaction.replace(R.id.container, Profile())
                    charSequence == "Cart" -> transaction.replace(R.id.container, Cart())
                    charSequence == "My Orders" -> transaction.replace(R.id.container, MyOrders())
                }
                transaction.commit()
                true
            }
            bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        }

         }

    override fun openCartFragment() {
        val cartFragment = Cart()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, cartFragment)
            .addToBackStack(null)
            .commit()
    }
}