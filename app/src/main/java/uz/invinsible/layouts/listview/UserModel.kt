package uz.invinsible.layouts.listview

class UserModel(private val name: String, private val image: Int, private val subtitle: String) {

    fun getName(): String {
        return name
    }

    fun getImage(): Int {
        return image
    }

    fun getSubtitle(): String {
        return subtitle
    }
}