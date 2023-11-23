package com.nino.productsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nino.productsapp.util.Converters

@Dao
interface ProductsDao {
    @Query("select * from DatabaseProductDetails")
    fun getDatabaseProducts(): LiveData<List<DatabaseProductDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseProductDetails>)

    @Query("select * from DatabaseProductDetails WHERE title LIKE :title")
    fun getProductDetails(title: String): LiveData<DatabaseProductDetails>

    @Query("select * from DatabaseProductDetails WHERE category LIKE :category")
    fun getProductsByCategory(category: String): LiveData<List<DatabaseProductDetails>>

    @Query("delete from DatabaseProductDetails")
    fun deleteProducts()
}

@Database(entities = [DatabaseProductDetails::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ProductsDatabase : RoomDatabase() {
    abstract val productsDao: ProductsDao
}