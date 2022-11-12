package uz.invinsible.layouts.media_player

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.databinding.VideoPlayerActivityLayoutBinding

class VideoPlayerActivity : AppCompatActivity() {

    lateinit var binding: VideoPlayerActivityLayoutBinding
    private var controller: MediaController? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VideoPlayerActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (controller == null) {
            controller = MediaController(this)
        }
        binding.videoViewId.setVideoURI(Uri.parse("android.resource://$packageName/raw/video"))
        binding.videoViewId.setMediaController(controller)
        binding.videoViewId.requestFocus()
        binding.videoViewId.start()
    }
}