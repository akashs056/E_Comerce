package com.example.ecomerce.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomerce.Activity.ProductDetailActivity
import com.example.ecomerce.Models.AddProductModel
import com.example.ecomerce.databinding.SampleCategoryProductBinding
import kotlinx.coroutines.NonDisposableHandle
import kotlinx.coroutines.NonDisposableHandle.parent

class CategoryProductAdapter(val context : Context,val list: ArrayList<AddProductModel>) :
RecyclerView.Adapter<CategoryProductAdapter.viewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=SampleCategoryProductBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        Glide.with(context).load(list[position].productCoverImg).into(holder.binding.imageView2)
        holder.binding.textView5.text=list[position].productName
        holder.binding.textView6.text=list[position].productSp

        holder.itemView.setOnClickListener {
         val intent=Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }
    }

    inner class viewHolder(val binding:SampleCategoryProductBinding):RecyclerView.ViewHolder(binding.root){
    }

}