package org.wit.archaeologicalfieldwork.models.hillfort

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.archaeologicalfieldwork.helpers.exists
import org.wit.archaeologicalfieldwork.helpers.read
import org.wit.archaeologicalfieldwork.helpers.write
import org.wit.archaeologicalfieldwork.models.comment.CommentsModel
import java.util.Random

val JSON_FILE = "hillforts.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<HillfortModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class HillfortJSONStore(val context: Context) : HillfortStore, AnkoLogger {
    var hillforts = mutableListOf<HillfortModel>()

    init {
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): List<HillfortModel> {
        return hillforts
    }

    override fun findAllComments(hillfort: HillfortModel): List<CommentsModel> {
        var foundHillfort: HillfortModel? = hillforts.find { h -> h.id == hillfort.id }
        return foundHillfort!!.comments
    }

    override fun findById(id: Long): HillfortModel {
        return hillforts.find { h -> h.id == id }!!
    }

    override fun create(hillfort: HillfortModel) {
        hillfort.id = generateRandomId()
        hillforts.add(hillfort)
        serialize()
    }

    override fun update(hillfort: HillfortModel) {
        var foundHillfort: HillfortModel? = hillforts.find { h -> h.id == hillfort.id }
        if (foundHillfort != null) {
            foundHillfort.name = hillfort.name
            foundHillfort.description = hillfort.description
            foundHillfort.images = hillfort.images
            foundHillfort.location = hillfort.location
            foundHillfort.comments = hillfort.comments
            serialize()
        }
    }

    override fun delete(hillfort: HillfortModel) {
        hillforts.remove(hillfort)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(hillforts, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        hillforts = Gson().fromJson(jsonString, listType)
    }
}