package uz.invinsible.layouts.media_player

import android.app.Activity
import android.content.Intent
import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import uz.invinsible.layouts.R
import uz.invinsible.layouts.databinding.AudioPlayerActivityLayoutBinding
import java.io.File
import java.net.URI


class AudioPlayerActivity : AppCompatActivity() {

    lateinit var binding: AudioPlayerActivityLayoutBinding

    private lateinit var runnable: Runnable
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AudioPlayerActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val audio = MediaPlayer.create(this, R.raw.audio1)
        binding.seekbarId.progress = 0
        binding.seekbarId.max = audio.duration
        binding.audioFullId.text = convertMinuteSecondFromMillisecond(audio.duration)
//        binding.audioTitle.text = getTitle("res/raw/audio1.mp3")
        binding.playId.setOnClickListener {
            if (!audio.isPlaying) {
                audio.start()
                binding.playId.setImageResource(R.drawable.ic_pause)
            } else {
                audio.pause()
                binding.playId.setImageResource(R.drawable.ic_play)
            }
        }

        binding.seekbarId.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekbar: SeekBar?, position: Int, bool: Boolean) {
                binding.audioStartId.text =
                    convertMinuteSecondFromMillisecond(audio.currentPosition)
                audio.seekTo(position)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        runnable = Runnable {
            binding.audioStartId.text = convertMinuteSecondFromMillisecond(audio.currentPosition)
            binding.seekbarId.progress = audio.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        audio.setOnCompletionListener {
            binding.seekbarId.progress = 0
            binding.playId.setImageResource(R.drawable.ic_play)
        }
    }

    fun convertMinuteSecondFromMillisecond(mSecond: Int): String {
        val minute = mSecond / (1000 * 60)
        val second = mSecond % (1000 * 60) / 1000
        val finalMin = if (minute < 10) {
            "0$minute"
        } else {
            "$minute"
        }

        val finalSec = if (second < 10) {
            "0$second"
        } else {
            "$second"
        }

        return "$finalMin:$finalSec"
    }

    fun getTitle(path: String): String {

        val file = File(path)

        val fileName: String = file.name

        val bytes: Long = file.length()
        val fileSize = String.format("%.2f", bytes / 1024) + " kb"

        Log.d("tag", "$fileName $fileSize")

        val mediaMetadataRetriever = MediaMetadataRetriever()
        val uri: Uri = Uri.fromFile(file)
        mediaMetadataRetriever.setDataSource(this, uri)

        val songName =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        val artist =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val album =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        val genre =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)
        val track =
            mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS)

        return songName.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (requestCode == RC_MEDIA_FILE && resultCode == Activity.RESULT_OK && intent != null) {
            val mmr = MediaMetadataRetriever()
            mmr.setDataSource(this, intent.data)

            val title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
            val artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
            val duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)

        }
    }

    companion object {
        const val RC_MEDIA_FILE = 100
    }
}