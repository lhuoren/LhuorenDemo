/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gac.nioapp.test.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.bean.Album;
import com.gac.nioapp.test.bean.IncapableCause;
import com.gac.nioapp.test.bean.Item;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.model.SelectedItemCollection;
import com.gac.nioapp.test.utils.PathUtils;
import com.gac.nioapp.test.widget.CheckView;
import com.gac.nioapp.test.widget.MediaGrid;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AlbumMediaAdapter extends
        RecyclerViewCursorAdapter<RecyclerView.ViewHolder> implements
        MediaGrid.OnMediaGridClickListener {

    private static final int VIEW_TYPE_CAPTURE = 0x01;
    private static final int VIEW_TYPE_MEDIA = 0x02;
    private final SelectedItemCollection mSelectedCollection;
    private final Drawable mPlaceholder;
    private SelectionSpec mSelectionSpec;
    private CheckStateListener mCheckStateListener;
    private OnMediaClickListener mOnMediaClickListener;
    private RecyclerView mRecyclerView;
    private int mImageResize;

    private Map<String, Boolean> existsMap;
    private Context mContext;

    public AlbumMediaAdapter(Context context, SelectedItemCollection selectedCollection, RecyclerView recyclerView) {
        super(null);
        mSelectionSpec = SelectionSpec.getInstance();
        mSelectedCollection = selectedCollection;
        mContext = context;

        mSelectedCollection.setDefaultUriSelection(mSelectionSpec.selectedList);

        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
        mPlaceholder = ta.getDrawable(0);
        ta.recycle();

        mRecyclerView = recyclerView;
        existsMap = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_CAPTURE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_capture_item, parent, false);
            CaptureViewHolder holder = new CaptureViewHolder(v);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getContext() instanceof OnPhotoCapture) {
                        ((OnPhotoCapture) v.getContext()).capture();
                    }
                }
            });
            return holder;
        } else if (viewType == VIEW_TYPE_MEDIA) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_grid_item, parent, false);
            return new MediaViewHolder(v);
        }
        return null;
    }

    @Override
    protected void onBindViewHolder(final RecyclerView.ViewHolder holder, Cursor cursor) {
        if (holder instanceof CaptureViewHolder) {
           /* CaptureViewHolder captureViewHolder = (CaptureViewHolder) holder;
            Drawable[] drawables = captureViewHolder.mHint.getCompoundDrawables();
            TypedArray ta = holder.itemView.getContext().getTheme().obtainStyledAttributes(
                    new int[]{R.attr.capture_textColor});
            int color = ta.getColor(0, 0);
            ta.recycle();

            for (int i = 0; i < drawables.length; i++) {
                Drawable drawable = drawables[i];
                if (drawable != null) {
                    final Drawable.ConstantState state = drawable.getConstantState();
                    if (state == null) {
                        continue;
                    }

                    Drawable newDrawable = state.newDrawable().mutate();
                    newDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
                    newDrawable.setBounds(drawable.getBounds());
                    drawables[i] = newDrawable;
                }
            }
            captureViewHolder.mHint.setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);*/
        } else if (holder instanceof MediaViewHolder) {
            MediaViewHolder mediaViewHolder = (MediaViewHolder) holder;

            final Item item = Item.valueOf(cursor);

            boolean isExists = true;

            if (item.isVideo()) {
                String uriString = item.getContentUri().toString();
                if (existsMap.containsKey(uriString)) {
                    isExists = existsMap.get(uriString);
                } else {
                    String path = PathUtils.getPath(mContext, item.getContentUri());

                    if (path != null) {
                        if (!existsMap.containsKey(path)) {
                            File file = new File(path);
                            isExists = file.exists();
                            existsMap.put(uriString, isExists);
                        } else {
                            isExists = existsMap.get(path);
                        }
                    } else {
                        existsMap.put(uriString, false);
                    }
                }
            }

            mediaViewHolder.mMediaGrid.preBindMedia(new MediaGrid.PreBindInfo(
                    getImageResize(mediaViewHolder.mMediaGrid.getContext()),
                    mPlaceholder,
                    mSelectionSpec.countable,
                    holder
            ));
            mediaViewHolder.mMediaGrid.bindMedia(item, isExists);
            setCheckStatus(item, mediaViewHolder.mMediaGrid);

            if (item.isVideo()) {

                boolean enable = true;
                if (!isExists) {
                    enable = false;
                } else if (
                        item.duration < mSelectionSpec.minVideoDuration ||
                                item.duration > mSelectionSpec.maxVideoDuration
                ) {
                    enable = false;
                } else if (item.size > mSelectionSpec.maxVideoFileSize) {
                    enable = false;
                }
                mediaViewHolder.mMediaGrid.setCheckEnabled(enable);
            }

            mediaViewHolder.mMediaGrid.setOnMediaGridClickListener(this);
        }
    }

    private void setCheckStatus(Item item, MediaGrid mediaGrid) {

        if (!mSelectionSpec.showCheckView && mediaGrid.mCheckView != null) {
            mediaGrid.mCheckView.setVisibility(View.GONE);
            return;
        }

        if (item.isVideo() && mSelectionSpec.videoAutoPlay) {
            mediaGrid.mCheckView.setVisibility(View.GONE);
            return;
        }
        mediaGrid.mCheckView.setVisibility(View.VISIBLE);
        if (mSelectionSpec.countable) {
            int checkedNum = mSelectedCollection.checkedNumOf(item);
            if (checkedNum > 0) {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setCheckedNum(checkedNum);
            } else {
                if (mSelectedCollection.maxSelectableReached()) {
                    mediaGrid.setCheckEnabled(false);
                    mediaGrid.setCheckedNum(CheckView.UNCHECKED);
                } else {
                    mediaGrid.setCheckEnabled(true);
                    mediaGrid.setCheckedNum(checkedNum);
                }
            }
        } else {
            boolean selected = mSelectedCollection.isSelected(item);
            if (selected) {
                mediaGrid.setCheckEnabled(true);
                mediaGrid.setChecked(true);
            } else {
                if (mSelectedCollection.maxSelectableReached()) {
                    mediaGrid.setCheckEnabled(false);
                    mediaGrid.setChecked(false);
                } else {
                    mediaGrid.setCheckEnabled(true);
                    mediaGrid.setChecked(false);
                }
            }
        }
    }

    private boolean checkSelectVideo(Item item) {
        if (item.isVideo()) {
            Boolean isExists = existsMap.get(item.getContentUri().toString());
            if (isExists != null && !isExists) {
                Toast.makeText(mContext, "视频文件不存在或已损坏", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (item.duration < mSelectionSpec.minVideoDuration) {
                String tip = "无法选择" + mSelectionSpec.minVideoDuration / 1000 + "秒内的视频";
                Toast.makeText(mContext, tip, Toast.LENGTH_SHORT).show();
                return false;
            }
            if (item.duration > mSelectionSpec.maxVideoDuration) {
                String tip = "无法选择" + mSelectionSpec.maxVideoDuration / 1000 + "秒以上的视频";
                Toast.makeText(mContext, tip, Toast.LENGTH_SHORT).show();
                return false;
            }
            if (item.size > mSelectionSpec.maxVideoFileSize) {
                String tip = "视频不能超过" + mSelectionSpec.maxVideoFileSize / 1024 / 1024 + "M";
                Toast.makeText(mContext, tip, Toast.LENGTH_SHORT).show();
                return false;
            }

            return true;

        }

        return false;
    }

    @Override
    public void onThumbnailClicked(ImageView thumbnail, Item item, RecyclerView.ViewHolder holder) {
        if (item.isVideo()) {
            if (!checkSelectVideo(item)) {
                return;
            }
        } else {

            if (item.isGif()) {
                String path = PathUtils.getPath(mContext, item.getContentUri());
                long size = PathUtils.getFileSize(path);

                if (size > 10 * 1024 * 1024) {
                    Toast.makeText(mContext, "选择的GIF文件不能超过10M", Toast.LENGTH_SHORT).show();

                    return;
                }

            }
        }


        if (mOnMediaClickListener != null) {
            mOnMediaClickListener.onMediaClick(null, item, holder.getAdapterPosition());
        }
    }


    @Override
    public void onCheckViewClicked(CheckView checkView, Item item, RecyclerView.ViewHolder holder) {
        if (item.isVideo()) {
            if (!checkSelectVideo(item)) {
                return;
            }
        } else {
            if (item.isGif()) {
                String path = PathUtils.getPath(mContext, item.getContentUri());
                long size = PathUtils.getFileSize(path);

                if (size > 10 * 1024 * 1024) {
                    Toast.makeText(mContext, "选择的GIF文件不能超过10M", Toast.LENGTH_SHORT).show();

                    return;
                }

            }
        }

        if (mSelectionSpec.countable) {
            int checkedNum = mSelectedCollection.checkedNumOf(item);
            if (checkedNum == CheckView.UNCHECKED) {
                if (assertAddSelection(holder.itemView.getContext(), item)) {
                    mSelectedCollection.add(item);
                    notifyCheckStateChanged();
                }
            } else {
                mSelectedCollection.remove(item);
                notifyCheckStateChanged();
            }
        } else {
            if (mSelectedCollection.isSelected(item)) {
                mSelectedCollection.remove(item);
                notifyCheckStateChanged();
            } else {
                if (assertAddSelection(holder.itemView.getContext(), item)) {
                    mSelectedCollection.add(item);
                    notifyCheckStateChanged();
                }
            }
        }
    }

    private void notifyCheckStateChanged() {
        notifyDataSetChanged();
        if (mCheckStateListener != null) {
            mCheckStateListener.onUpdate();
        }
    }

    @Override
    public int getItemViewType(int position, Cursor cursor) {
        return Item.valueOf(cursor).isCapture() ? VIEW_TYPE_CAPTURE : VIEW_TYPE_MEDIA;
    }

    private boolean assertAddSelection(Context context, Item item) {
        IncapableCause cause = mSelectedCollection.isAcceptable(item);
        //IncapableCause.handleCause(context, cause);
        return cause == null;
    }

    public void registerCheckStateListener(CheckStateListener listener) {
        mCheckStateListener = listener;
    }

    public void unregisterCheckStateListener() {
        mCheckStateListener = null;
    }

    public void registerOnMediaClickListener(OnMediaClickListener listener) {
        mOnMediaClickListener = listener;
    }

    public void unregisterOnMediaClickListener() {
        mOnMediaClickListener = null;
    }

    public void refreshSelection() {
        GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        int first = layoutManager.findFirstVisibleItemPosition();
        int last = layoutManager.findLastVisibleItemPosition();
        if (first == -1 || last == -1) {
            return;
        }
        Cursor cursor = getCursor();
        for (int i = first; i <= last; i++) {
            RecyclerView.ViewHolder holder = mRecyclerView.findViewHolderForAdapterPosition(first);
            if (holder instanceof MediaViewHolder) {
                if (cursor.moveToPosition(i)) {
                    setCheckStatus(Item.valueOf(cursor), ((MediaViewHolder) holder).mMediaGrid);
                }
            }
        }
    }

    private int getImageResize(Context context) {
        if (mImageResize == 0) {
            RecyclerView.LayoutManager lm = mRecyclerView.getLayoutManager();
            int spanCount = ((GridLayoutManager) lm).getSpanCount();
            int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
            int availableWidth = screenWidth - context.getResources().getDimensionPixelSize(
                    R.dimen.media_grid_spacing) * (spanCount - 1);
            mImageResize = availableWidth / spanCount;
            mImageResize = (int) (mImageResize * mSelectionSpec.thumbnailScale);
        }
        return mImageResize;
    }

    public interface CheckStateListener {
        void onUpdate();
    }

    public interface OnMediaClickListener {
        void onMediaClick(Album album, Item item, int adapterPosition);
    }

    public interface OnPhotoCapture {
        void capture();
    }

    private static class MediaViewHolder extends RecyclerView.ViewHolder {

        private MediaGrid mMediaGrid;

        MediaViewHolder(View itemView) {
            super(itemView);
            mMediaGrid = (MediaGrid) itemView;
        }
    }

    private static class CaptureViewHolder extends RecyclerView.ViewHolder {

        private TextView mHint;

        CaptureViewHolder(View itemView) {
            super(itemView);

            //mHint = (TextView) itemView.findViewById(R.id.hint);
        }
    }

}
