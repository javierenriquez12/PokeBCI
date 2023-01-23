package com.bci.data.data.entity


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class PokedexEntity(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String?=null,
    @SerializedName("results")
    val results: List<com.bci.data.data.entity.ResultEntity>
)