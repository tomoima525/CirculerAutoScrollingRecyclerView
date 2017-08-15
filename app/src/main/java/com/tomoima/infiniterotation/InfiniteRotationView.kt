package com.tomoima.infiniterotation

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.HORIZONTAL
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import butterknife.BindView
import butterknife.ButterKnife

/**
 * Created by tomoaki on 2017/08/13.
 */
class InfiniteRotationView(context: Context, attributeSet: AttributeSet)
    : RelativeLayout(context, attributeSet) {

    @BindView(R.id.recyclerView_horizontalList)
    internal lateinit var recyclerView: RecyclerView

    private val layoutManager: LinearLayoutManager
    private lateinit var onScrollListener: OnScrollListener

    init {
        View.inflate(context, R.layout.view_infinite_rotation, this)
        ButterKnife.bind(this)
        layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
    }

    fun setAdapter(adapter: InfiniteRotationAdapter) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        adapter.itemCount
                .takeIf { it > 1 }
                ?.apply {
                    onScrollListener = OnScrollListener(adapter.itemCount, layoutManager)
                    recyclerView.addOnScrollListener(onScrollListener)
                    recyclerView.scrollToPosition(1)
                }
    }

    class OnScrollListener(
            val itemCount: Int,
            val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val firstItemVisible = layoutManager.findFirstVisibleItemPosition()

            if (firstItemVisible > 0 && firstItemVisible % (itemCount - 1) == 0) {
                // When position reaches end of the list, it should go back to the beginning
                recyclerView?.scrollToPosition(1)
            } else if (firstItemVisible == 0) {
                // When position reaches beginning of the list, it should go back to the end
                recyclerView?.scrollToPosition(itemCount - 1)
            }
        }
    }
}