package com.wooyj.datalayer.data.remote.api

import com.wooyj.datalayer.data.remote.dto.BreedListDTO
import com.wooyj.datalayer.data.remote.dto.DogImageListDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *
 * Dog API 기반으로 연동
 * @see <a href="https://dog.ceo/dog-api/">Dog API</a>
 *
 */
interface DogApi {
    @GET(ApiUrl.API_BREED_LIST)
    suspend fun getBreedList(): Response<List<BreedListDTO>>

    @GET(ApiUrl.API_BREED_IMAGE_LIST)
    suspend fun getBreedImageList(
        @Path("breedName") breedName: String,
    ): Response<List<DogImageListDTO>>

    @GET(ApiUrl.API_SUBBREED_IMAGE_LIST)
    suspend fun getSubBreedImageList(
        @Path("breedName") breedName: String,
        @Path("subBreedName") subBreedName: String,
    ): Response<List<DogImageListDTO>>
}
