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
package com.gac.nioapp.test.fragment;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gac.nioapp.test.R;
import com.gac.nioapp.test.bean.Item;
import com.gac.nioapp.test.bean.SelectionSpec;
import com.gac.nioapp.test.listener.OnFragmentInteractionListener;
import com.gac.nioapp.test.utils.PhotoMetadataUtils;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class PreviewItemFragment extends Fragment {

    private static final String ARGS_ITEM = "args_item";
    private OnFragmentInteractionListener mListener;

    public static PreviewItemFragment newInstance(Item item) {
        PreviewItemFragment fragment = new PreviewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_ITEM, item);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_item, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Item item = getArguments().getParcelable(ARGS_ITEM);
        if (item == null) {
            return;
        }

        View videoPlayButton = view.findViewById(R.id.video_play_button);
        VideoView videoView = view.findViewById(R.id.video);
        View ivPause = view.findViewById(R.id.iv_pause);
        videoView.setVisibility(View.GONE);
        videoView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                ivPause.setVisibility(videoView.isPlaying() ? View.VISIBLE : View.GONE);
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
            }
            return true;
        });

        ivPause.setOnClickListener(v -> {
            ivPause.setVisibility(View.GONE);
            videoView.start();
        });
        if (item.isVideo()) {

            if (SelectionSpec.getInstance().videoAutoPlay) {
                videoPlayButton.setVisibility(View.GONE);
                videoView.setVisibility(View.VISIBLE);
                videoView.setVideoURI(item.uri);
                videoView.setOnPreparedListener(mp -> {
                    mp.setLooping(true);
                    mp.start();
                });
                videoView.start();
            } else {
                videoPlayButton.setVisibility(View.VISIBLE);
                videoPlayButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(item.uri, "video/*");
                        try {
                            startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getContext(), R.string.error_no_video_activity, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } else {
            videoPlayButton.setVisibility(View.GONE);
        }

        ImageViewTouch image = (ImageViewTouch) view.findViewById(R.id.image_view);
        image.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);

        image.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener() {
            @Override
            public void onSingleTapConfirmed() {
                if (mListener != null) {
                    mListener.onClick();
                }
            }
        });

        Point size = PhotoMetadataUtils.getBitmapSize(item.getContentUri(), getActivity());
        if (item.isGif()) {
            SelectionSpec.getInstance().imageEngine.loadGifImage(getContext(), size.x, size.y, image,
                    item.getContentUri());
        } else {
            SelectionSpec.getInstance().imageEngine.loadImage(getContext(), size.x, size.y, image,
                    item.getContentUri());
        }

        image.setVisibility(SelectionSpec.getInstance().videoAutoPlay && item.isVideo() ? View.GONE : View.VISIBLE);
    }

    public void resetView() {
        if (getView() != null) {
            ((ImageViewTouch) getView().findViewById(R.id.image_view)).resetMatrix();
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
