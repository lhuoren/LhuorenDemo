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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.adapter.PreviewPagerAdapter;
import com.gac.nioapp.test.bean.IncapableCause;
import com.gac.nioapp.test.bean.Item;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.fragment.PreviewItemFragment;
import com.gac.nioapp.test.listener.OnFragmentInteractionListener;
import com.gac.nioapp.test.model.SelectedItemCollection;
import com.gac.nioapp.test.utils.Platform;
import com.gac.nioapp.test.widget.CheckView;

public abstract class BasePreviewActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener, OnFragmentInteractionListener {

    public static final String EXTRA_DEFAULT_BUNDLE = "extra_default_bundle";
    public static final String EXTRA_RESULT_BUNDLE = "extra_result_bundle";
    public static final String EXTRA_RESULT_APPLY = "extra_result_apply";

    protected final SelectedItemCollection mSelectedCollection = new SelectedItemCollection(this);
    protected SelectionSpec mSpec;
    protected ViewPager mPager;

    protected PreviewPagerAdapter mAdapter;

    protected CheckView mCheckView;
    //protected TextView mButtonBack;
    protected View mBackLayout;
    protected TextView mButtonApply;
    //protected TextView mSize;

    protected int mPreviousPos = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(SelectionSpec.getInstance().themeId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_preview);
        if (Platform.hasKitKat()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        mSpec = SelectionSpec.getInstance();
        if (mSpec.needOrientationRestriction()) {
            setRequestedOrientation(mSpec.orientation);
        }

        if (savedInstanceState == null) {
            mSelectedCollection.onCreate(getIntent().getBundleExtra(EXTRA_DEFAULT_BUNDLE));
        } else {
            mSelectedCollection.onCreate(savedInstanceState);
        }

        //mButtonBack = (TextView) findViewById(R.id.button_back);
        mBackLayout = findViewById(R.id.fl_back);
        mButtonApply = (TextView) findViewById(R.id.tv_confirm);
        //mSize = (TextView) findViewById(R.id.size);
        mBackLayout.setOnClickListener(this);
        mButtonApply.setOnClickListener(this);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.addOnPageChangeListener(this);
        mAdapter = new PreviewPagerAdapter(getSupportFragmentManager(), null);
        mPager.setAdapter(mAdapter);
        mCheckView = (CheckView) findViewById(R.id.check_view);
        mCheckView.setCountable(mSpec.countable);

        mCheckView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Item item = mAdapter.getMediaItem(mPager.getCurrentItem());
                if (mSelectedCollection.isSelected(item)) {
                    mSelectedCollection.remove(item);
                    if (mSpec.countable) {
                        mCheckView.setCheckedNum(CheckView.UNCHECKED);
                    } else {
                        mCheckView.setChecked(false);
                    }
                } else {
                    if (assertAddSelection(item)) {
                        mSelectedCollection.add(item);
                        if (mSpec.countable) {
                            mCheckView.setCheckedNum(mSelectedCollection.checkedNumOf(item));
                        } else {
                            mCheckView.setChecked(true);
                        }
                    }
                }
                updateApplyButton();
            }
        });
        updateApplyButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mSelectedCollection.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        sendBackResult(false);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fl_back) {
            onBackPressed();
        } else if (v.getId() == R.id.tv_confirm) {
            sendBackResult(true);
            finish();
        }
    }

    @Override
    public void onClick() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        PreviewPagerAdapter adapter = (PreviewPagerAdapter) mPager.getAdapter();
        if (mPreviousPos != -1 && mPreviousPos != position) {
            ((PreviewItemFragment) adapter.instantiateItem(mPager, mPreviousPos)).resetView();

            Item item = adapter.getMediaItem(position);
            if (mSpec.countable) {
                int checkedNum = mSelectedCollection.checkedNumOf(item);
                mCheckView.setCheckedNum(checkedNum);
                if (checkedNum > 0) {
                    mCheckView.setEnabled(true);
                } else {
                    mCheckView.setEnabled(!mSelectedCollection.maxSelectableReached());
                }
            } else {
                boolean checked = mSelectedCollection.isSelected(item);
                mCheckView.setChecked(checked);
                if (checked) {
                    mCheckView.setEnabled(true);
                } else {
                    mCheckView.setEnabled(!mSelectedCollection.maxSelectableReached());
                }
            }
            updateSize(item);
        }
        mPreviousPos = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    protected void updateApplyButton() {
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

    protected void updateSize(Item item) {
        if (item.isGif()) {
            //mSize.setVisibility(View.VISIBLE);
            //mSize.setText(PhotoMetadataUtils.getSizeInMB(item.size) + "M");
        } else {
            //mSize.setVisibility(View.GONE);
        }
    }

    protected void sendBackResult(boolean apply) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_RESULT_BUNDLE, mSelectedCollection.getDataWithBundle());
        intent.putExtra(EXTRA_RESULT_APPLY, apply);
        setResult(Activity.RESULT_OK, intent);
    }

    protected boolean assertAddSelection(Item item) {
        IncapableCause cause = mSelectedCollection.isAcceptable(item);
        IncapableCause.handleCause(this, cause);
        return cause == null;
    }
}
