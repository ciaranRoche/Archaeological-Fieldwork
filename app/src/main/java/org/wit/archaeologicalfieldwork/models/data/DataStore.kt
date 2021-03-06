package org.wit.archaeologicalfieldwork.models.data

import android.graphics.Bitmap

interface DataStore {
    suspend fun findAll(): ArrayList<DataModel>
    suspend fun findById(id: Long): DataModel?
    suspend fun create(data: DataModel)
    suspend fun update(data: DataModel)
    suspend fun delete(data: DataModel)
    suspend fun deleteByFbif()
    suspend fun findFavorites(): ArrayList<DataModel>
    fun updateImage(image: String, hillfort: DataModel)
    fun updateBitMapImage(image: Bitmap, name: String, hillfort: DataModel)
    fun clear()
}