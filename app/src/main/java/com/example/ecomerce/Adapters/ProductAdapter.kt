package com.example.ecomerce.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomerce.Activity.ProductDetailActivity
import com.example.ecomerce.Models.AddProductModel
import com.example.ecomerce.databinding.SampleProductsBinding

class ProductAdapter (val context: Context, val list:ArrayList<AddProductModel>):RecyclerView.Adapter<ProductAdapter.viewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding=SampleProductsBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val data=list[position]
        Glide.with(context).load(data.productCoverImg).into(holder.binding.productImg)
        holder.binding.textView4.text=data.productName
        holder.binding.textView3.text=data.productCategory
        holder.binding.textView2.text=data.productMrp
        holder.binding.button.text=data.productSp
        holder.itemView.setOnClickListener {
            val intent= Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }
    }
    inner class viewHolder(val binding: SampleProductsBinding):RecyclerView.ViewHolder(binding.root)

}