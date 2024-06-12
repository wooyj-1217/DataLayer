package com.wooyj.datalayer.data.remote.api

class ApiUrl {
    companion object {
        const val API_BREED_LIST = "breeds/list/all"
        const val API_BREED_IMAGE_LIST = "breed/{breedName}/images"
        const val API_SUBBREED_IMAGE_LIST = "breed/{breedName}/{subBreedName}/images"
    }
}
// TODO("위와 같이 compaion object로 감싸서 const val 쓰는것과 그냥 파일에 const val 쓰는건 어떤 차이가 있을까요..? 그리고 둘중에 어느것이 더 올바른 방법일까요..?")
