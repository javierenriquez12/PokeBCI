package com.bci.data.data.entity


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ResultEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)