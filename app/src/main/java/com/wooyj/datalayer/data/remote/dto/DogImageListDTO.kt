package com.wooyj.datalayer.data.remote.dto

// https://dog.ceo/api/breed/hound/images
// https://dog.ceo/api/breed/hound/afghan/images
data class DogImageListDTO(
    val message: List<String>,
    val status: String,
)
