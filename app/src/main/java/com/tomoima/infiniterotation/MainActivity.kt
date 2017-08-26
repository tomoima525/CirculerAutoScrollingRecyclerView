package com.tomoima.infiniterotation

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import butterknife.ButterKnife

class MainActivity : AppCompatActivity() {

    @BindView(R.id.infinite_rotation_view)
    internal lateinit var rotationView: InfiniteRotationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val items = createItems()
        rotationView.setAdapter(InfiniteRotationAdapter(items))
        rotationView.autoScroll(items.size, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        rotationView.stopAutoScroll()
    }

    private fun createItems() = Array(4, { i ->
        ItemInfo(i.toString(),
                Color.rgb(
                        (Math.random() * 255).toInt(),
                        (Math.random() * 255).toInt(),
                        (Math.random() * 255).toInt()))
    }).toList()
}
