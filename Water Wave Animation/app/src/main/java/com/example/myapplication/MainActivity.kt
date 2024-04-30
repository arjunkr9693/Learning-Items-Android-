// src/main/java/com/example/MainActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var waveView: WaveView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        waveView = findViewById(R.id.waveView)
        val circularImageView: ImageView = findViewById(R.id.circularImageView)
        val startButton: Button = findViewById(R.id.startButton)

        circularImageView.setOnClickListener {
            waveView.startWaveAnimation()
        }

        startButton.setOnClickListener {
            waveView.startWaveAnimation()
        }
    }
}
