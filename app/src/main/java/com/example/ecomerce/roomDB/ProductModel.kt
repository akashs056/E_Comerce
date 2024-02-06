package com.example.ecomerce.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    @NotNull
    val productid : String,

    @ColumnInfo(name = "productName")
    val productName : String,

    @ColumnInfo(name = "productImage")
    val productImage : String,

    @ColumnInfo(name = "productSp")
    val productSp : String,

)
