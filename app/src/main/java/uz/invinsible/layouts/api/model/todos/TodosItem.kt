package uz.invinsible.layouts.api.model.todos

data class TodosItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)