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

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.adapter.PreviewPagerAdapter;
import com.gac.nioapp.test.bean.Album;
import com.gac.nioapp.test.bean.Item;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.model.AlbumMediaCollection;
import com.gac.nioapp.test.widget.CheckView;

import java.util.ArrayList;
import java.util.List;

public class AlbumPreviewActivity extends BasePreviewActivity implements
        AlbumMediaCollection.AlbumMediaCallbacks {

    public static final String EXTRA_ALBUM = "extra_album";
    public static final String EXTRA_ITEM = "extra_item";

    private AlbumMediaCollection mCollection = new AlbumMediaCollection();

    private boolean mIsAlreadySetPosition;

    private boolean isVideo;

    private Item item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCollection.onCreate(this, this);
        Album album = getIntent().getParcelableExtra(EXTRA_ALBUM);
        mCollection.load(album);

        item = getIntent().getParcelableExtra(EXTRA_ITEM);
        isVideo = item.isVideo();
        if (mSpec.countable) {
            mCheckView.setCheckedNum(mSelectedCollection.checkedNumOf(item));
        } else {
            mCheckView.setChecked(mSelectedCollection.isSelected(item));
        }

        if (isVideoAutoPlay()) {
            mCheckView.setVisibility(View.GONE);
        }

        updateSize(item);


        mCheckView.setOnClickListener(v -> {
            Item item1 = mAdapter.getMediaItem(mPager.getCurrentItem());
            if (mSelectedCollection.isSelected(item1)) {
                mSelectedCollection.remove(item1);
                if (mSpec.countable) {
                    mCheckView.setCheckedNum(CheckView.UNCHECKED);
                } else {
                    mCheckView.setChecked(false);
                }
            } else {
                if (assertAddSelection(item1)) {
                    mSelectedCollection.add(item1);
                    if (mSpec.countable) {
                        mCheckView.setCheckedNum(mSelectedCollection.checkedNumOf(item1));
                    } else {
                        mCheckView.setChecked(true);
                    }
                }
            }
            updateApplyButton();
        });
        updateApplyButton();
    }

    boolean isVideoAutoPlay() {
        return isVideo && SelectionSpec.getInstance().videoAutoPlay;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCollection.onDestroy();
    }

    @Override
    public void onAlbumMediaLoad(Cursor cursor) {
        List<Item> items = new ArrayList<>();
        if (isVideoAutoPlay()) {
            mIsAlreadySetPosition = true;
            items.add(item);
        } else {
            while (cursor.moveToNext()) {
                Item i = Item.valueOf(cursor);
                if (!i.isVideo()) {
                    items.add(Item.valueOf(cursor));
                }
            }
        }

        PreviewPagerAdapter adapter = (PreviewPagerAdapter) mPager.getAdapter();
        adapter.addAll(items);

        adapter.notifyDataSetChanged();
        if (!mIsAlreadySetPosition) {
            //onAlbumMediaLoad is called many times..
            mIsAlreadySetPosition = true;
            Item selected = getIntent().getParcelableExtra(EXTRA_ITEM);
            int selectedIndex = items.indexOf(selected);
            mPager.setCurrentItem(selectedIndex, false);
            mPreviousPos = selectedIndex;
        }
    }

    @Override
    public void onAlbumMediaReset() {

    }

    @Override
    protected void sendBackResult(boolean apply) {
        if (apply && isVideoAutoPlay()) {
            mSelectedCollection.clear();
            mSelectedCollection.add(mAdapter.getMediaItem(mPager.getCurrentItem()));
        }
        super.sendBackResult(apply);
    }

    @Override
    protected void updateApplyButton() {
        if (isVideoAutoPlay()) {
            mButtonApply.setEnabled(true);
            mButtonApply.setText(getString(R.string.button_confirm_default));
            return;
        }
        super.updateApplyButton();
    }
}
