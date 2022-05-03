package com.example.android.demo1

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class BasicCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    init {
        isClickable = true
    }

    var paintBackground = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
    }

    var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        color = Color.WHITE
        strokeWidth = 4f
    }

    var paintHole = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        textAlign = Paint.Align.CENTER
        color = Color.BLACK
        strokeWidth = 4f
    }

    // Bitmap b = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
    // profileImage.setImageBitmap(Bitmap.createScaledBitmap(b, 120, 120, false));
    var logo: Bitmap =
        BitmapFactory.decodeResource(context.resources, R.drawable.apple)
    private var logo2: Bitmap? = null

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val minSize = min(w, h)
        logo2 = Bitmap.createScaledBitmap(logo, minSize / 4, minSize / 4, false)
    }

    override fun performClick(): Boolean {
        Log.d("BasicCustomView", "performClick: Clicked")
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 배경색
        // drawARGB, drawRGB, drawColor, drawPaint 사용가능
        canvas.drawColor(Color.GRAY)

        // center
        val halfY = (height / 2).toFloat()
        val halfX = (width / 2).toFloat()
        // center x
        canvas.drawLine(0f, halfY, width.toFloat(), halfY, paint)
        // center y
        canvas.drawLine(halfX, 0f, halfX, height.toFloat(), paint)

        var logoX: Float = 0f
        var logoY: Float = 0f
        val centerX = width / 2
        val centerY = height / 2
        if (logo2 != null) {
            val logoHalfX = logo2!!.width / 2
            val logoHalfY = logo2!!.height / 2

            logoX = (centerX - logoHalfX).toFloat()
            logoY = (centerY - logoHalfY).toFloat()
        }

        // center image
        logo2?.let { logo ->
            canvas.drawBitmap(logo, logoX, logoY, paint)
        }

        // 테두리
        canvas.drawRect(
            logoX,
            logoY,
            (centerX + (logo2!!.width / 2)).toFloat(),
            (centerY + (logo2!!.height / 2)).toFloat(),
            paintHole
        )
    }
}