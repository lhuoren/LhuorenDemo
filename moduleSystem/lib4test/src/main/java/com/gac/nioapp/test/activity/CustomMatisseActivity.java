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
package com.gac.nioapp.test.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.adapter.AlbumMediaAdapter;
import com.gac.nioapp.test.bean.Album;
import com.gac.nioapp.test.bean.Item;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.fragment.MediaSelectionFragment;
import com.gac.nioapp.test.model.AlbumCollection;
import com.gac.nioapp.test.model.SelectedItemCollection;
import com.gac.nioapp.test.theme.DayNightManager;
import com.gac.nioapp.test.utils.DensityUtil;
import com.gac.nioapp.test.utils.MediaStoreCompat;
import com.gac.nioapp.test.utils.MimeType;
import com.gac.nioapp.test.utils.PathUtils;
import com.gac.nioapp.test.utils.StatusBarUtil;
import com.gac.nioapp.test.widget.AlbumPopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Activity to display albums and media content (images/videos) in each album
 * and also support media selecting operations.
 */
public class CustomMatisseActivity extends AppCompatActivity implements
        AlbumCollection.AlbumCallbacks, AlbumPopupWindow.OnMenuItemClickListener,
        MediaSelectionFragment.SelectionProvider, View.OnClickListener,
        AlbumMediaAdapter.CheckStateListener, AlbumMediaAdapter.OnMediaClickListener,
        AlbumMediaAdapter.OnPhotoCapture {

    public static final String EXTRA_RESULT_SELECTION = "extra_result_selection";
    public static final String EXTRA_RESULT_LIST = "extra_result_list";
    public static final String EXTRA_RESULT_SELECTION_PATH = "extra_result_selection_path";
    public static final String EXTRA_IS_CAPTURE = "extra_is_capture";

    public static final String EXTRA_NEXT_INTENT = "extra_next_intent";
    private static final int REQUEST_CODE_PREVIEW = 23;
    private static final int REQUEST_CODE_CAPTURE = 24;
    private final AlbumCollection mAlbumCollection = new AlbumCollection();
    private MediaStoreCompat mMediaStoreCompat;
    private SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    private SelectionSpec mSpec;

    private AlbumPopupWindow mAlbumsSpinner;

    //private TextView mButtonPreview;
    private TextView mButtonApply;
    private TextView mTvTitle;

    private ImageView mArrow;
    private View mContainer;
    private View mBackGroud, mTitleLayout;
    private View mEmptyView;
    private View mDividerView;

    private ImageView mIvBack;

    private Toolbar toolbar;

    private Intent nextIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // programmatically set theme before super.onCreate()
        mSpec = SelectionSpec.getInstance();
        setTheme(mSpec.themeId);
        super.onCreate(savedInstanceState);
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果您的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果您是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则您可以选择性注释掉这个if内容
        boolean isDark = !DayNightManager.isNightMode(this); //状态栏普通模式为黑色，暗夜模式为白色
        setStatusBar(isDark);
        DensityUtil.setDefault(this);
        setContentView(R.layout.activity_custom_matisse);

        nextIntent = getIntent().getParcelableExtra(EXTRA_NEXT_INTENT);

        if (mSpec.needOrientationRestriction()) {
            setRequestedOrientation(mSpec.orientation);
        }

        if (mSpec.capture) {
            mMediaStoreCompat = new MediaStoreCompat(this);
            if (mSpec.captureStrategy == null)
                throw new RuntimeException("Don't forget to set CaptureStrategy.");
            mMediaStoreCompat.setCaptureStrategy(mSpec.captureStrategy);
        }

        //mButtonPreview = (TextView) findViewById(R.id.button_preview);
        mButtonApply = (TextView) findViewById(R.id.tv_confirm);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mArrow = findViewById(R.id.iv_arrow);
        toolbar = findViewById(R.id.toolbar);
        mDividerView = findViewById(R.id.split_divider_view);
        mTitleLayout = findViewById(R.id.ll_title);
        mBackGroud = findViewById(R.id.view_bg);
        //mButtonPreview.setOnClickListener(this);
        mButtonApply.setOnClickListener(this);
        mIvBack = findViewById(R.id.iv_back);
        mIvBack.setOnClickListener(this);
        mTitleLayout.setOnClickListener(this);

        mContainer = findViewById(R.id.container);
        mEmptyView = findViewById(R.id.empty_view);

        mSelectedCollection.onCreate(savedInstanceState);
        updateBottomToolbar();

        //mAlbumsAdapter = new CustomAlbumsAdapter(this, null);
        mAlbumsSpinner = new AlbumPopupWindow(this);
        mAlbumsSpinner.setOnMenuItemClickListener(this);
        //mAlbumsSpinner.setSelectedTextView((TextView) findViewById(R.id.selected_album));
        //mAlbumsSpinner.setPopupAnchorView(findViewById(R.id.toolbar));
        // mAlbumsSpinner.setAdapter(mAlbumsAdapter);

        mAlbumCollection.onCreate(this, this);
        mAlbumCollection.onRestoreInstanceState(savedInstanceState);
        mAlbumCollection.loadAlbums();

        mAlbumsSpinner.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mArrow.setImageResource(R.drawable.ic_triangle_down);
                mBackGroud.setVisibility(View.GONE);
            }
        });

        //这里修改成直接拍照，调用相机
        if (mSpec.onlyTakePicture) {
            capture();
        }
    }

    public void setStatusBar(boolean isDark) {
        if (!StatusBarUtil.setStatusBarDarkTheme(this, isDark)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mSelectedCollection.onSaveInstanceState(outState);
        mAlbumCollection.onSaveInstanceState(outState);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAlbumsSpinner != null)
            mAlbumsSpinner.destroy();
        mAlbumCollection.onDestroy();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onBackPressed() {
        setCustomResult(Activity.RESULT_CANCELED);
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //只是拍照的情况下，从相机返回，应该直接关闭当前页面，偷懒了，这里，其实应该另外写一个拍照逻辑的，但是直接套用Matisse的了，谁叫它这么优秀
        if (resultCode != RESULT_OK && mSpec.onlyTakePicture) {
            finish();
        }
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_CODE_PREVIEW) {
            Bundle resultBundle = data.getBundleExtra(BasePreviewActivity.EXTRA_RESULT_BUNDLE);
            ArrayList<Item> selected = resultBundle.getParcelableArrayList(SelectedItemCollection.STATE_SELECTION);
            int collectionType = resultBundle.getInt(SelectedItemCollection.STATE_COLLECTION_TYPE,
                    SelectedItemCollection.COLLECTION_UNDEFINED);
            if (data.getBooleanExtra(BasePreviewActivity.EXTRA_RESULT_APPLY, false)) {
                Intent result = getCustomIntent();
                ArrayList<Uri> selectedUris = new ArrayList<>();
                ArrayList<String> selectedPaths = new ArrayList<>();
                //ArrayList<Item> selectedItems = new ArrayList<>();
                if (selected != null) {
                    for (Item item : selected) {
                        selectedUris.add(item.getContentUri());
                        try {
                            selectedPaths.add(PathUtils.getPath(this, item.getContentUri()));
                        } catch (Exception e) {
                            //e.printStackTrace();
                            selectedPaths.add(item.uri.toString());
                        }

                        //selectedItems.add(item);
                    }
                }
                result.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, selectedUris);
                result.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, selectedPaths);
                //result.putParcelableArrayListExtra(EXTRA_RESULT_LIST, selectedItems);
                setCustomResult(RESULT_OK, result);
                finish();
            } else {
                mSelectedCollection.overwrite(selected, collectionType);
                Fragment mediaSelectionFragment = getSupportFragmentManager().findFragmentByTag(
                        MediaSelectionFragment.class.getSimpleName());
                if (mediaSelectionFragment instanceof MediaSelectionFragment) {
                    ((MediaSelectionFragment) mediaSelectionFragment).refreshMediaGrid();
                }
                updateBottomToolbar();
            }
        } else if (requestCode == REQUEST_CODE_CAPTURE) {
            // Just pass the data back to previous calling Activity.
            Uri contentUri = mMediaStoreCompat.getCurrentPhotoUri();
            String path = mMediaStoreCompat.getCurrentPhotoPath();
            ArrayList<Uri> selected = new ArrayList<>();
            selected.add(contentUri);
            ArrayList<String> selectedPath = new ArrayList<>();
            selectedPath.add(path);
            Intent result = getCustomIntent();

            result.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, selected);
            result.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, selectedPath);
            result.putExtra(EXTRA_IS_CAPTURE, true);
            setCustomResult(RESULT_OK, result);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
                CustomMatisseActivity.this.revokeUriPermission(contentUri,
                        Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            finish();
        }
    }

    public void updateBottomToolbar() {
        int selectedCount = mSelectedCollection.count();
        if (selectedCount == 0) {
            mButtonApply.setEnabled(false);
            mButtonApply.setText(getString(R.string.button_confirm_default));
        } else if (selectedCount == 1 && mSpec.singleSelectionModeEnabled()) {
            mButtonApply.setText(R.string.button_confirm_default);
            mButtonApply.setEnabled(true);
        } else {
            mButtonApply.setEnabled(true);
            mButtonApply.setText(getString(R.string.button_confirm_1, selectedCount));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            onBackPressed();
        } else if (v.getId() == R.id.ll_title) {
            if (!mAlbumsSpinner.isShowing()) {
                mArrow.setImageResource(R.drawable.ic_triangle_up);
                mAlbumsSpinner.showAsDropDown(mDividerView, 0, 0);

                mBackGroud.setVisibility(View.VISIBLE);
            } else {
                mAlbumsSpinner.dismiss();
            }
        } else if (v.getId() == R.id.tv_confirm) {
            Intent result = getCustomIntent();
            ArrayList<Uri> selectedUris = (ArrayList<Uri>) mSelectedCollection.asListOfUri();
            result.putParcelableArrayListExtra(EXTRA_RESULT_SELECTION, selectedUris);
            ArrayList<String> selectedPaths = (ArrayList<String>) mSelectedCollection.asListOfString();
            result.putStringArrayListExtra(EXTRA_RESULT_SELECTION_PATH, selectedPaths);
            setCustomResult(RESULT_OK, result);
            finish();
        }
    }

    @Override
    public void onMenuItemClick(Album album, int position) {
        mAlbumCollection.setStateCurrentSelection(position);
        mTvTitle.setText(album.getDisplayName(this));
        //mAlbumsAdapter.getCursor().moveToPosition(position);
        //Album album = Album.valueOf(mAlbumsAdapter.getCursor());
        if (album.isAll() && SelectionSpec.getInstance().capture) {
            album.addCaptureCount();
        }
        onAlbumSelected(album);
    }

    @Override
    public void onAlbumLoad(final Cursor cursor) {
        mAlbumsSpinner.swapCursor(cursor);
        // select default album.
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                cursor.moveToPosition(mAlbumCollection.getCurrentSelection());
                mAlbumsSpinner.setSelection(CustomMatisseActivity.this,
                        mAlbumCollection.getCurrentSelection());
                Album album = Album.valueOf(cursor);
                mTvTitle.setText(album.getDisplayName(CustomMatisseActivity.this));
                if (album.isAll() && SelectionSpec.getInstance().capture) {
                    album.addCaptureCount();
                }
                onAlbumSelected(album);
            }
        });
    }

    @Override
    public void onAlbumReset() {
        mAlbumsSpinner.swapCursor(null);
    }

    private void onAlbumSelected(Album album) {
        if (album.isAll() && album.isEmpty()) {
            mContainer.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mContainer.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
            Fragment fragment = MediaSelectionFragment.newInstance(album);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment, MediaSelectionFragment.class.getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    public void onUpdate() {
        // notify bottom toolbar that check state changed.
        updateBottomToolbar();
    }

    @Override
    public void onMediaClick(Album album, Item item, int adapterPosition) {
        if (item.isVideo() && !MimeType.supportUploadVideoFormat(item.mimeType)) {
            Toast.makeText(this, "暂不支持该视频文件类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (item.isVideo() && mSelectedCollection.typeConflict(item)) {
            mSpec.selectionListener.event(SelectionSpec.EventType.TYPE_CONFLICT, null, this);
            return;
        }
        Intent intent = new Intent(this, AlbumPreviewActivity.class);
        intent.putExtra(AlbumPreviewActivity.EXTRA_ALBUM, album);
        intent.putExtra(AlbumPreviewActivity.EXTRA_ITEM, item);
        intent.putExtra(BasePreviewActivity.EXTRA_DEFAULT_BUNDLE, mSelectedCollection.getDataWithBundle());
        startActivityForResult(intent, REQUEST_CODE_PREVIEW);
    }

    @Override
    public SelectedItemCollection provideSelectedItemCollection() {
        return mSelectedCollection;
    }

    @Override
    public void capture() {
        if (checkPermission()) {
            if (mMediaStoreCompat != null) {
                mMediaStoreCompat.dispatchCaptureIntent(this, REQUEST_CODE_CAPTURE);
            }
        }
    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }

            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this,
                        permissions.toArray(new String[0]),
                        100);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                for (int ret : grantResults) {
                    if (ret != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }

                if (mMediaStoreCompat != null) {
                    mMediaStoreCompat.dispatchCaptureIntent(this, REQUEST_CODE_CAPTURE);
                }
                break;
            default:
                break;
        }
    }

    private Intent getCustomIntent() {
        if (nextIntent == null) {
            return new Intent();
        }
        return nextIntent;
    }

    private void setCustomResult(int resultCode) {
        setCustomResult(resultCode, null);
    }

    private void setCustomResult(int resultCode, Intent data) {

        if (nextIntent == null || data == null || resultCode != Activity.RESULT_OK) {
            setResult(resultCode, data);
            return;
        }

        startActivity(nextIntent);
    }


}
