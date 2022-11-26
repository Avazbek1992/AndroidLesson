package uz.invinsible.layouts.api

import retrofit2.Call
import retrofit2.http.GET
import uz.invinsible.layouts.api.model.albums.AlbumsItem
import uz.invinsible.layouts.api.model.comments.CommentsItem
import uz.invinsible.layouts.api.model.photos.PhotosItem
import uz.invinsible.layouts.api.model.posts.PostItem
import uz.invinsible.layouts.api.model.todos.TodosItem
import uz.invinsible.layouts.api.model.users.UsersItem


interface ApiInterface {
    @GET("posts")
    fun getPosts(): Call<List<PostItem>>

    @GET("comments")
    fun getComments(): Call<List<CommentsItem>>

    @GET("albums")
    fun getAlbums(): Call<List<AlbumsItem>>

    @GET("photos")
    fun getPhotos(): Call<List<PhotosItem>>

    @GET("todos")
    fun getTodos(): Call<List<TodosItem>>

    @GET("users")
    fun getUsers(): Call<List<UsersItem>>
}