package com.gac.nioapp.test.widget;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.adapter.CustomAlbumsAdapter;
import com.gac.nioapp.test.bean.Album;

/**
 * ClassName: AlbumPopupWindow
 *
 * @author pengl
 * @see
 */
public class AlbumPopupWindow extends PopupWindow implements CustomAlbumsAdapter.OnMediaClickListener{

    RecyclerView recyclerView;

    CustomAlbumsAdapter adapter;

    Context context;

    //String selectedText;

    private OnMenuItemClickListener listener;

    public interface OnMenuItemClickListener{
        void onMenuItemClick(Album album, int adapterPosition);
    }

    public AlbumPopupWindow(Context context) {
        View view = View.inflate(context, R.layout.menu_albums, null);

        this.context = context;

        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        setFocusable(true);
        setOutsideTouchable(true);
        setContentView(view);
        //update();

        convertView(view);
    }

    private void convertView(View view){
        recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new CustomAlbumsAdapter(context,null);
        adapter.registerOnMediaClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    public void swapCursor(Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    public void setSelection(Context context, int position) {
        onItemSelected(context, position);
    }

    private void onItemSelected(Context context, int position) {
        dismiss();
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);
        recyclerView.smoothScrollToPosition(position);
        Album album = Album.valueOf(cursor);
        String displayName = album.getDisplayName(context);
        adapter.setSelectedAlbumName(displayName);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onMediaClick(Album album, int adapterPosition) {
        dismiss();
        adapter.setSelectedAlbumName(album.getDisplayName(context));
        adapter.getCursor().moveToPosition(adapterPosition);

        if(listener != null){
            listener.onMenuItemClick(album,adapterPosition);
        }
    }


    public void destroy(){
        adapter.unregisterOnMediaClickListener();
        context = null;
    }

}
