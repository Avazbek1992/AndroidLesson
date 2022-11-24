package uz.invinsible.layouts.api

import retrofit2.Call
import retrofit2.http.GET
import uz.invinsible.layouts.api.model.comments.CommentsItem
import uz.invinsible.layouts.api.model.posts.PostItem


interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<PostItem>>

    @GET("comments")
    fun getComments(): Call<List<CommentsItem>>
}