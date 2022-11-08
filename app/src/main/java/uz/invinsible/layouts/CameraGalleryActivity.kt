package uz.invinsible.layouts

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.databinding.CameraGalleryLayoutBinding

class CameraGalleryActivity : AppCompatActivity() {
    lateinit var binding: CameraGalleryLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CameraGalleryLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraId.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        }

        binding.galleryId.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val uri = data?.data
            binding.cameraImgId.setImageURI(uri)
        } else {
            val bitmap: Bitmap = data?.extras?.get("data") as Bitmap
            binding.cameraImgId.setImageBitmap(bitmap)
        }
    }

}