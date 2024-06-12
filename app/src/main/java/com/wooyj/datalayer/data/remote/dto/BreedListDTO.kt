package com.wooyj.datalayer.data.remote.dto

// https://dog.ceo/api/breeds/list/all
data class BreedListDTO(
    val message: Map<String, List<String>>,
    val status: String,
)
