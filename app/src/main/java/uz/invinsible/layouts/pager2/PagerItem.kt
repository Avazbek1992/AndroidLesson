package uz.invinsible.layouts.pager2

class PagerItem(
//    private val imageID: Int,
//    private val title: String,
//    private val subtitle: String
) {//constructor

    private var imageID = 0
    private var title = ""
    private var subtitle = ""

    fun setImageID(imageID: Int){
        this.imageID = imageID
    }

    fun setTitle(title: String){
        this.title = title
    }

    fun setSubtitle(subtitle: String){
        this.subtitle = subtitle
    }

    fun getImageID(): Int {
        return this.imageID
    }

    fun getTitle(): String {
        return this.title
    }

    fun getSubtitle(): String {
        return this.subtitle
    }
}