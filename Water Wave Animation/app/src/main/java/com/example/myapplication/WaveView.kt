// src/main/java/com/example/WaveView.kt
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

class WaveView : View {

    private val wavePaint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val waveCount = 5
    private var waveWidth = 0
    private var waveHeight = 0

    private var waveProgress = 0f
    private val waveAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        interpolator = LinearInterpolator()
        repeatMode = ValueAnimator.RESTART
        repeatCount = ValueAnimator.INFINITE
        duration = 2000
        addUpdateListener { animation ->
            waveProgress = animation.animatedValue as Float
            invalidate()
        }
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        // Initialization if needed
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        waveWidth = w
        waveHeight = h / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until waveCount) {
            drawWave(canvas, i * waveWidth * 2.toFloat())
        }
    }

    private fun drawWave(canvas: Canvas, xOffset: Float) {
        val centerY = height / 2
        val startY = centerY + waveHeight * Math.sin((waveProgress + xOffset / width) * 2 * Math.PI).toFloat()
        val endY = centerY + waveHeight * Math.sin(((waveProgress + xOffset) / width + 0.5f) * 2 * Math.PI).toFloat()

        canvas.drawRect(xOffset, startY, xOffset + waveWidth, endY, wavePaint)
    }

    fun startWaveAnimation() {
        if (!waveAnimator.isRunning) {
            waveAnimator.start()
        }
    }

    fun stopWaveAnimation() {
        if (waveAnimator.isRunning) {
            waveAnimator.cancel()
        }
    }
}
