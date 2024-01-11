package com.lhuoren.custom.xrecycleview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

/**
 * Created by lhr on 15/11/22.
 */
class XRecyclerView : RecyclerView {

    companion object {
        private const val DRAG_RATE = 3f

        //下面的ItemViewType是保留值(ReservedItemViewType),如果用户的adapter与它们重复将会强制抛出异常。不过为了简化,我们检测到重复时对用户的提示是ItemViewType必须小于10000
        private const val TYPE_REFRESH_HEADER = 10000 //设置一个很大的数字,尽可能避免和用户的adapter冲突
        private const val TYPE_FOOTER = 10001
        private const val HEADER_INIT_INDEX = 10002
        private val sHeaderTypes: MutableList<Int> = ArrayList() //每个header必须有不同的type,不然滚动的时候顺序会变化
    }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private var isLoadingData = false
    private var isNoMore = false
    private var mRefreshProgressStyle = ProgressStyle.SysProgress
    private var mLoadingMoreProgressStyle = ProgressStyle.SysProgress
    private val mHeaderViews = ArrayList<View>()
    private var mWrapAdapter: WrapAdapter? = null
    private var mLastY = -1f
    private var mLoadingListener: LoadingListener? = null
    private var mRefreshHeader: ArrowRefreshHeader? = null
    private var mScrollListener: ScrollListener? = null
    private var pullRefreshEnabled = true
    private var loadingMoreEnabled = true
    private val mPageCount = 0

    //adapter没有数据的时候显示,类似于listView的emptyView
    private var mEmptyView: View? = null
    private var mFootView: View? = null
    private val mDataObserver: AdapterDataObserver = DataObserver()
    private var appbarState = AppBarStateChangeListener.State.EXPANDED
    private fun init() {
        if (pullRefreshEnabled) {
            mRefreshHeader = ArrowRefreshHeader(context)
            mRefreshHeader!!.setProgressStyle(mRefreshProgressStyle)
        }
        val footView = LoadingMoreFooter(context)
        footView.setProgressStyle(mLoadingMoreProgressStyle)
        mFootView = footView
        (mFootView as LoadingMoreFooter).visibility = GONE
    }

    fun addHeaderView(view: View) {
        sHeaderTypes.add(HEADER_INIT_INDEX + mHeaderViews.size)
        mHeaderViews.add(view)
    }

    //根据header的ViewType判断是哪个header
    private fun getHeaderViewByType(itemType: Int): View? {
        return if (!isHeaderType(itemType)) {
            null
        } else mHeaderViews[itemType - HEADER_INIT_INDEX]
    }

    //判断一个type是否为HeaderType
    private fun isHeaderType(itemViewType: Int): Boolean {
        return mHeaderViews.size > 0 && sHeaderTypes.contains(itemViewType)
    }

    //判断是否是XRecyclerView保留的itemViewType
    private fun isReservedItemViewType(itemViewType: Int): Boolean {
        return itemViewType == TYPE_REFRESH_HEADER || itemViewType == TYPE_FOOTER || sHeaderTypes.contains(
            itemViewType
        )
    }

    fun setFootView(view: View?) {
        mFootView = view
    }

    fun loadMoreComplete() {
        isLoadingData = false
        if (mFootView is LoadingMoreFooter) {
            (mFootView as LoadingMoreFooter).setState(LoadingMoreFooter.STATE_COMPLETE)
        } else {
            mFootView!!.visibility = GONE
        }
    }

    fun setNoMore(noMore: Boolean) {
        isLoadingData = false
        isNoMore = noMore
        if (mFootView is LoadingMoreFooter) {
            (mFootView as LoadingMoreFooter).setState(if (isNoMore) LoadingMoreFooter.STATE_NOMORE else LoadingMoreFooter.STATE_COMPLETE)
        } else {
            mFootView!!.visibility = GONE
        }
    }

    fun reset() {
        setNoMore(false)
        loadMoreComplete()
        refreshComplete()
    }

    fun refreshComplete() {
        mRefreshHeader!!.refreshComplete()
        setNoMore(false)
    }

    fun setRefreshHeader(refreshHeader: ArrowRefreshHeader?) {
        mRefreshHeader = refreshHeader
    }

    fun setPullRefreshEnabled(enabled: Boolean) {
        pullRefreshEnabled = enabled
    }

    fun setLoadingMoreEnabled(enabled: Boolean) {
        loadingMoreEnabled = enabled
        if (!enabled) {
            if (mFootView is LoadingMoreFooter) {
                (mFootView as LoadingMoreFooter).setState(LoadingMoreFooter.STATE_COMPLETE)
            }
        }
    }

    fun setRefreshProgressStyle(style: Int) {
        mRefreshProgressStyle = style
        if (mRefreshHeader != null) {
            mRefreshHeader!!.setProgressStyle(style)
        }
    }

    fun setLoadingMoreProgressStyle(style: Int) {
        mLoadingMoreProgressStyle = style
        if (mFootView is LoadingMoreFooter) {
            (mFootView as LoadingMoreFooter).setProgressStyle(style)
        }
    }

    fun setArrowImageView(resId: Int) {
        if (mRefreshHeader != null) {
            mRefreshHeader!!.setArrowImageView(resId)
        }
    }

    fun setEmptyView(emptyView: View?) {
        mEmptyView = emptyView
        mDataObserver.onChanged()
    }

    fun getEmptyView(): View? {
        return mEmptyView
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        mWrapAdapter = WrapAdapter(adapter as Adapter<ViewHolder>)
        super.setAdapter(mWrapAdapter)
        adapter?.registerAdapterDataObserver(mDataObserver)
        mDataObserver.onChanged()
    }

    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
        if (state == SCROLL_STATE_IDLE && mLoadingListener != null && !isLoadingData && loadingMoreEnabled) {
            val layoutManager = layoutManager
            val lastVisibleItemPosition: Int = when (layoutManager) {
                is GridLayoutManager -> {
                    layoutManager.findLastVisibleItemPosition()
                }

                is StaggeredGridLayoutManager -> {
                    val into =
                        IntArray(layoutManager.spanCount)
                    layoutManager.findLastVisibleItemPositions(
                        into
                    )
                    findMax(into)
                }

                else -> {
                    (layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
                }
            }
            if (layoutManager!!.childCount > 0 && lastVisibleItemPosition >= layoutManager.itemCount - 1 && layoutManager.itemCount > layoutManager.childCount && !isNoMore && mRefreshHeader!!.getState() < BaseRefreshHeader.STATE_REFRESHING) {
                isLoadingData = true
                if (mFootView is LoadingMoreFooter) {
                    (mFootView as LoadingMoreFooter).setState(LoadingMoreFooter.STATE_LOADING)
                } else {
                    mFootView!!.visibility = VISIBLE
                }
                mLoadingListener!!.onLoadMore()
            }
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        if (mLastY == -1f) {
            mLastY = ev.rawY
        }
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> mLastY = ev.rawY
            MotionEvent.ACTION_MOVE -> {
                val deltaY = ev.rawY - mLastY
                mLastY = ev.rawY
                Log.i(
                    "handlerUpdate", "mLastY:$mLastY,deltaY:$deltaY"
                )
                val isSignificantDelta: Boolean = abs(deltaY) > 1
                if (isSignificantDelta) {
                    if (deltaY > 0) {
                        mScrollListener?.onScrollDown()
                    } else {
                        mScrollListener?.onScrollUp()
                    }
                }

                if (isOnTop()) {
                    mScrollListener?.OnScrollTop()
                }

                if (isOnTop() && pullRefreshEnabled && appbarState === AppBarStateChangeListener.State.EXPANDED) {

                    mRefreshHeader!!.onMove(deltaY / DRAG_RATE)
                    Log.i(
                        "handlerUpdate", "onMove:$deltaY"
                    )
                    if (mRefreshHeader!!.getVisibleHeight() > 0 && mRefreshHeader!!.getState() < BaseRefreshHeader.STATE_REFRESHING) {
                        Log.i(
                            "handlerUpdate", "onTouchEvent:isOnTop:" + isOnTop()
                                    + ",pullRefreshEnabled:$pullRefreshEnabled"
                                    + ",appbarState:$appbarState"
                                    + ",AppBarStateChangeListener.State:" + (appbarState === AppBarStateChangeListener.State.EXPANDED)
                                    + ",getState:" + mRefreshHeader!!.getState()
                                    + ",getVisibleHeight:" + mRefreshHeader!!.getVisibleHeight()
                        )
                        return false
                    }
                }
            }

            else -> {
                mLastY = -1f // reset
                if (isOnTop() && pullRefreshEnabled && appbarState === AppBarStateChangeListener.State.EXPANDED) {
                    if (mRefreshHeader!!.releaseAction()) {
                        if (mLoadingListener != null) {
                            mLoadingListener!!.onRefresh()
                        }
                    }
                }
            }
        }
        return super.onTouchEvent(ev)
    }

    private fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (value in lastPositions) {
            if (value > max) {
                max = value
            }
        }
        return max
    }


    private fun isOnTop(): Boolean {
        return mRefreshHeader!!.parent != null
    }


    private inner class DataObserver : AdapterDataObserver() {
        override fun onChanged() {
            val adapter = adapter
            if (adapter != null && mEmptyView != null) {
                var emptyCount = 0
                if (pullRefreshEnabled) {
                    emptyCount++
                }
                if (loadingMoreEnabled) {
                    emptyCount++
                }
                if (adapter.itemCount == emptyCount) {
                    mEmptyView!!.visibility = VISIBLE
                    this@XRecyclerView.visibility = GONE
                } else {
                    mEmptyView!!.visibility = GONE
                    this@XRecyclerView.visibility = VISIBLE
                }
            }
            if (mWrapAdapter != null) {
                mWrapAdapter!!.notifyDataSetChanged()
            }
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            mWrapAdapter!!.notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            mWrapAdapter!!.notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            mWrapAdapter!!.notifyItemRangeChanged(positionStart, itemCount, payload)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            mWrapAdapter!!.notifyItemRangeRemoved(positionStart, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            mWrapAdapter!!.notifyItemMoved(fromPosition, toPosition)
        }
    }

    inner class WrapAdapter(private val adapter: Adapter<ViewHolder>) : Adapter<ViewHolder>() {
        fun isHeader(position: Int): Boolean {
            return position >= 1 && position < mHeaderViews.size + 1
        }

        fun isFooter(position: Int): Boolean {
            return if (loadingMoreEnabled) {
                position == itemCount - 1
            } else {
                false
            }
        }

        fun isRefreshHeader(position: Int): Boolean {
            return position == 0
        }

        fun getHeadersCount(): Int {
            return mHeaderViews.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            if (viewType == TYPE_REFRESH_HEADER) {
                return SimpleViewHolder(mRefreshHeader)
            } else if (isHeaderType(viewType)) {
                return SimpleViewHolder(getHeaderViewByType(viewType))
            } else if (viewType == TYPE_FOOTER) {
                return SimpleViewHolder(mFootView)
            }
            return adapter!!.onCreateViewHolder(parent, viewType)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (isHeader(position) || isRefreshHeader(position)) {
                return
            }
            val adjPosition = position - (getHeadersCount() + 1)
            val adapterCount: Int
            if (adapter != null) {
                adapterCount = adapter.itemCount
                if (adjPosition < adapterCount) {
                    adapter.run { onBindViewHolder(holder, adjPosition) }
                    return
                }
            }
        }

        override fun getItemCount(): Int {
            return if (loadingMoreEnabled) {
                if (adapter != null) {
                    getHeadersCount() + adapter.itemCount + 2
                } else {
                    getHeadersCount() + 2
                }
            } else {
                if (adapter != null) {
                    getHeadersCount() + adapter.itemCount + 1
                } else {
                    getHeadersCount() + 1
                }
            }
        }

        override fun getItemViewType(position: Int): Int {
            var position = position
            val adjPosition = position - (getHeadersCount() + 1)
            check(!isReservedItemViewType(adapter!!.getItemViewType(adjPosition))) { "XRecyclerView require itemViewType in adapter should be less than 10000 " }
            if (isRefreshHeader(position)) {
                return TYPE_REFRESH_HEADER
            }
            if (isHeader(position)) {
                position -= 1
                return sHeaderTypes[position]
            }
            if (isFooter(position)) {
                return TYPE_FOOTER
            }
            val adapterCount: Int
            if (adapter != null) {
                adapterCount = adapter.itemCount
                if (adjPosition < adapterCount) {
                    return adapter.getItemViewType(adjPosition)
                }
            }
            return 0
        }

        override fun getItemId(position: Int): Long {
            if (adapter != null && position >= getHeadersCount() + 1) {
                val adjPosition = position - (getHeadersCount() + 1)
                if (adjPosition < adapter.itemCount) {
                    return adapter.getItemId(adjPosition)
                }
            }
            return -1
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            val manager = recyclerView.layoutManager
            if (manager is GridLayoutManager) {
                manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (isHeader(position) || isFooter(position) || isRefreshHeader(
                                position
                            )
                        ) manager.spanCount else 1
                    }
                }
            }
            adapter!!.onAttachedToRecyclerView(recyclerView)
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            adapter!!.onDetachedFromRecyclerView(recyclerView)
        }

        override fun onViewAttachedToWindow(holder: ViewHolder) {
            super.onViewAttachedToWindow(holder)
            val lp = holder.itemView.layoutParams
            if (lp != null && lp is StaggeredGridLayoutManager.LayoutParams
                && (isHeader(holder.layoutPosition) || isRefreshHeader(holder.layoutPosition) || isFooter(
                    holder.layoutPosition
                ))
            ) {
                lp.isFullSpan = true
            }
            adapter!!.onViewAttachedToWindow(holder)
        }

        override fun onViewDetachedFromWindow(holder: ViewHolder) {
            adapter!!.onViewDetachedFromWindow(holder)
        }

        override fun onViewRecycled(holder: ViewHolder) {
            adapter!!.onViewRecycled(holder)
        }

        override fun onFailedToRecycleView(holder: ViewHolder): Boolean {
            return adapter!!.onFailedToRecycleView(holder)
        }

        override fun unregisterAdapterDataObserver(observer: AdapterDataObserver) {
            adapter!!.unregisterAdapterDataObserver(observer)
        }

        override fun registerAdapterDataObserver(observer: AdapterDataObserver) {
            adapter!!.registerAdapterDataObserver(observer)
        }

        private inner class SimpleViewHolder(itemView: View?) : ViewHolder(
            itemView!!
        )
    }

    fun setLoadingListener(listener: LoadingListener?) {
        mLoadingListener = listener
    }

    interface LoadingListener {
        fun onRefresh()
        fun onLoadMore()
    }

    fun setScrollListener(listener: ScrollListener?) {
        mScrollListener = listener
    }

    interface ScrollListener {
        fun onScrollUp()
        fun onScrollDown()
        fun OnScrollTop()
    }

    fun setRefreshing(refreshing: Boolean) {
        if (refreshing && pullRefreshEnabled && mLoadingListener != null) {
            mRefreshHeader!!.setState(BaseRefreshHeader.STATE_REFRESHING)
            mRefreshHeader!!.onMove(mRefreshHeader!!.measuredHeight.toFloat())
            /**注释掉第一次有加载不需要重复走了否则会因为清空list导致出错 */
//            mLoadingListener.onRefresh();
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        //解决和CollapsingToolbarLayout冲突的问题
        var appBarLayout: AppBarLayout? = null
        var p = parent
        while (p != null) {
            if (p is CoordinatorLayout) {
                break
            }
            p = p.parent
        }
        if (p is CoordinatorLayout) {
            val coordinatorLayout = p
            val childCount = coordinatorLayout.childCount
            for (i in childCount - 1 downTo 0) {
                val child = coordinatorLayout.getChildAt(i)
                if (child is AppBarLayout) {
                    appBarLayout = child
                    break
                }
            }
            appBarLayout?.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
                override fun onStateChanged(appBarLayout: AppBarLayout?, state: State?) {
                    if (state != null) {
                        appbarState = state
                    }
                }
            })
        }
    }

}