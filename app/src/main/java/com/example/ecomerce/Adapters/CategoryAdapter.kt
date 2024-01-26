package com.example.ecomerce.Adapters
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecomerce.Activity.CategoryActivity
import com.example.ecomerce.Models.CategoryModel
import com.example.ecomerce.R
import com.example.ecomerce.databinding.SampleCategoryBinding


class CategoryAdapter(private  val context : Context, private  val list: ArrayList<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_category,parent,false))
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.textView.text=list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.imageView)
        holder.itemView.setOnClickListener{
            val intent=Intent(context,CategoryActivity::class.java)
            intent.putExtra("cate",list[position].cate)
            context.startActivity(intent)
        }
    }
    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = SampleCategoryBinding.bind(view)
    }
}