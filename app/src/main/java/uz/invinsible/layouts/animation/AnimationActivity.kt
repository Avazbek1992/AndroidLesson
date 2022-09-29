package uz.invinsible.layouts.animation

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R

class AnimationActivity : AppCompatActivity() {

    var imageView: ImageView? = null
    var alpha: Animation? = null
    var rotate: Animation? = null
    var scale: Animation? = null
    var translate: Animation? = null
    var set: Animation? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_layout)

        imageView = findViewById(R.id.anim_image_id)
        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha)
        rotate = AnimationUtils.loadAnimation(this, R.anim.rotate)
        scale = AnimationUtils.loadAnimation(this, R.anim.scale)
        translate = AnimationUtils.loadAnimation(this, R.anim.translate)
        set = AnimationUtils.loadAnimation(this, R.anim.set)

        findViewById<Button>(R.id.alpha_btn_id)
            .setOnClickListener {
                imageView?.startAnimation(alpha)
            }
        findViewById<Button>(R.id.rotate_btn_id)
            .setOnClickListener {
                imageView?.startAnimation(rotate)
            }
        findViewById<Button>(R.id.scale_btn_id)
            .setOnClickListener {
                imageView?.startAnimation(scale)
            }
        findViewById<Button>(R.id.translate_btn_id)
            .setOnClickListener {
                imageView?.startAnimation(translate)
            }
        findViewById<Button>(R.id.set_btn_id)
            .setOnClickListener {
                imageView?.startAnimation(set)
            }
    }
}