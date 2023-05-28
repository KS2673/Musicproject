import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.musicproject_1.MainActivity
import com.example.musicproject_1.R
import com.example.musicproject_1.databinding.ActivityMainBinding

open class MainActivity2 : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var nextbutton:Button
    private lateinit var seekBar: SeekBar
    private lateinit var playButton: Button
    private lateinit var handler: Handler
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        setclick()



        seekBar = findViewById(R.id.musicseek)
        playButton = findViewById(R.id.playPauseButton)
        nextbutton = findViewById(R.id.nxtbtn)
        mediaPlayer = MediaPlayer.create(this, R.raw.tum_tak_02)
        handler = Handler(Looper.getMainLooper())

        seekBar.max = mediaPlayer.duration


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(2000)
                }
            }


            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.pause()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                mediaPlayer.start()
            }

        })



        mediaPlayer.setOnSeekCompleteListener(object : MediaPlayer.OnSeekCompleteListener {
            override fun onSeekComplete(mp: MediaPlayer) {
                // Handle seek complete event here
            }
        })

        mediaPlayer.isLooping = true

        playButton.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()


                playButton.setBackgroundResource(R.drawable.baseline_play_circle_24)
            } else {
                mediaPlayer.start()
                playButton.setBackgroundResource(R.drawable.baseline_pause_circle_24)
            }
        }
        handler.post(object : Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 1000)
            }
        })
    }
        private fun  setclick() = nextbutton.setOnClickListener(View.OnClickListener {
            val send = Intent(this@MainActivity2, MainActivity::class.java)
            send.putExtra("myData", "Hello from the 2nd activity!")
            startActivity(send)
        })



    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}



