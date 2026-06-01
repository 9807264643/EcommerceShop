package com.sanvi.ecommercetarget.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.sanvi.ecommercetarget.screen.home.Model.Product
import kotlinx.coroutines.flow.Flow

// DAO : for handling operation related to cart like save data, retrieve and delete data
// DAO ===> Data Access object (it means dao is interface or abstract class which allow to access data object from database(SQLite or room)

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: Product)

    @Update
    suspend fun updateCartItem(cartItem: Product)

    @Delete
    suspend fun deleteCartItem(cartItem: Product)

    @Query("SELECT * FROM cart_items" )
    fun getAllCartItem(): Flow<List<Product>>

    @Query("SELECT * FROM cart_items WHERE id = :productId")
    suspend fun getCartItemById(productId : String): Product

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

}