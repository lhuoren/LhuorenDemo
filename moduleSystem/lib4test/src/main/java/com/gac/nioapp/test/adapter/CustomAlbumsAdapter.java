package com.gac.nioapp.test.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.bean.Album;
import com.gac.nioapp.test.bean.SelectionSpec;

import java.io.File;

public class CustomAlbumsAdapter extends
        RecyclerViewCursorAdapter<RecyclerView.ViewHolder> {

    private final Drawable mPlaceholder;
    private String mSelectedAlbumName;
    private SelectionSpec mSelectionSpec;
    private OnMediaClickListener mOnMediaClickListener;

    public CustomAlbumsAdapter(Context context, String selectedAlbumName) {
        super(null);
        mSelectionSpec = SelectionSpec.getInstance();
        mSelectedAlbumName = selectedAlbumName;

        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{R.attr.item_placeholder});
        mPlaceholder = ta.getDrawable(0);
        ta.recycle();
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder viewHolder, Cursor cursor) {
        AlbumViewHolder holder = (AlbumViewHolder) viewHolder;
        final Album album = Album.valueOf(cursor);
        final Context context = holder.tvAlbumName.getContext();

        String displayName = album.getDisplayName(context);
        String name = context.getResources().getString(R.string.text_album_name, displayName, album.getCount());
        holder.tvAlbumName.setText(name);

        // do not need to load animated Gif
        SelectionSpec.getInstance().imageEngine.loadThumbnail(context, context.getResources().getDimensionPixelSize(R
                        .dimen.media_grid_size_1), mPlaceholder,
                holder.ivAlbumIcon, Uri.fromFile(new File(album.getCoverPath())));

        if (TextUtils.equals(displayName, mSelectedAlbumName)) {
            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.backgroundPlaceholder));
        } else {
            holder.itemLayout.setBackgroundColor(context.getResources().getColor(R.color.backgroundMainView));
        }

        final int position = viewHolder.getLayoutPosition();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMediaClickListener != null) {
                    mOnMediaClickListener.onMediaClick(album, position);
                }
            }
        });
    }

    public void setSelectedAlbumName(String name) {
        this.mSelectedAlbumName = name;
        notifyDataSetChanged();
    }

    public void registerOnMediaClickListener(OnMediaClickListener listener) {
        mOnMediaClickListener = listener;
    }

    public void unregisterOnMediaClickListener() {
        mOnMediaClickListener = null;
    }


    @Override
    protected int getItemViewType(int position, Cursor cursor) {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_albums_select, parent, false);
        return new AlbumViewHolder(rootView);
    }

    private static class AlbumViewHolder extends RecyclerView.ViewHolder {

        public TextView tvAlbumName;
        public ImageView ivAlbumIcon;
        public View itemLayout;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.ll_item);
            tvAlbumName = itemView.findViewById(R.id.tv_album_name);
            ivAlbumIcon = itemView.findViewById(R.id.iv_album_cover);
        }
    }


    public interface OnMediaClickListener {
        void onMediaClick(Album album, int adapterPosition);
    }
}
