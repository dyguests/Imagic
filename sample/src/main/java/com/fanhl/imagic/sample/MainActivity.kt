package com.fanhl.imagic.sample

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.ByteBuffer
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable = getDrawable(R.drawable.img_dog)
        val bitmapDrawable = drawable as? BitmapDrawable ?: return
        val bitmap = bitmapDrawable.bitmap ?: return

        val destBitmap: Bitmap = filter2(bitmap)

        img_2.setImageBitmap(destBitmap)

        Log.d(TAG, "onCreate: ")
    }

    private fun filter2(bitmap: Bitmap): Bitmap {
        val data = bitmap.run { IntArray(width * height) }
        bitmap.apply {
            getPixels(data, 0, width, 0, 0, width, height)
        }

        TODO("")
    }

    private fun filter(bitmap: Bitmap): Bitmap {
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount).also {
            bitmap.copyPixelsToBuffer(it)
        }
        val byteArray = byteBuffer.array()

        val destByteArray = byteArray.map { max(0, it - 50).toByte() }

        val destByteBuffer = ByteBuffer.wrap(destByteArray.toByteArray())

        val destBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        destBitmap.copyPixelsFromBuffer(destByteBuffer)
        return destBitmap
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
