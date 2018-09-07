package com.fanhl.imagic.sample

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.nio.ByteBuffer

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawable = getDrawable(R.mipmap.ic_launcher)
        val bitmapDrawable = drawable as? BitmapDrawable ?: return
        val bitmap = bitmapDrawable.bitmap
        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount).also {
            bitmap.copyPixelsToBuffer(it)
        }

        Log.d(TAG, "onCreate: ")
    }
}
