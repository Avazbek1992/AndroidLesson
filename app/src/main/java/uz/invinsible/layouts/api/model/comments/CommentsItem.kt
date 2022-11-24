package uz.invinsible.layouts.api.model.comments

data class CommentsItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int,
)