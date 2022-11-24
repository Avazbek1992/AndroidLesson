package uz.invinsible.layouts.api

import retrofit2.Call
import retrofit2.http.GET
import uz.invinsible.layouts.api.model.MyCommentsItem
import uz.invinsible.layouts.api.model.MyDataItem


interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<MyDataItem>>

    @GET("comments")
    fun getComments(): Call<List<MyCommentsItem>>
}