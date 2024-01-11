package com.gac.nioapp.test.view.topbar;

import android.content.Context;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import com.gac.nioapp.test.R;


public class EditToolbar extends Toolbar {

    ImageView mBtnBack;

    ImageView mBtnDelect;

    EditText mEditText;

    TextView mTitle;

    private OnClickListener mOnBackClickListener;
    private TextWatcher mTextWatcher;

    public EditToolbar(Context context) {
        this(context, null, 0);
    }

    public EditToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tool_bar_edit, null);
        mTitle = view.findViewById(R.id.tv_title);
        mEditText = view.findViewById(R.id.edit_query);
        mBtnDelect = view.findViewById(R.id.iv_delect);
        mBtnBack = view.findViewById(R.id.iv_back);
        view.findViewById(R.id.ll_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnBackClickListener != null) {
                    mOnBackClickListener.onClick(view);
                }
            }
        });
        mBtnDelect.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditText();
                InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        addView(view);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    mBtnDelect.setVisibility(View.INVISIBLE);
                } else {
                    mBtnDelect.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mEditText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_UP) {
                    return true;
                }
                return false;
            }
        });
    }


    public void setOnBackClickListener(OnClickListener listener) {
        mOnBackClickListener = listener;
    }

    public void setTextChangedListener(TextWatcher textWatcher) {
        if (mTextWatcher != null) {
            mEditText.removeTextChangedListener(mTextWatcher);
        }
        mEditText.addTextChangedListener(textWatcher);
        mTextWatcher = textWatcher;
    }

    public void setEditHint(String hint) {
        if (hint != null) {
            SpannableString ss = new SpannableString(" " + hint);//定义hint的值
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);//设置字体大小 true表示单位是sp
            ss.setSpan(ass, 1, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ass = new AbsoluteSizeSpan(18, true);
            ss.setSpan(ass, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mEditText.setHint(new SpannedString(ss));
        }
    }

    private void clearEditText() {
        mEditText.setText("");
    }

    public void hideEdit() {
        mEditText.setVisibility(View.GONE);
        mBtnDelect.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        hideEdit();
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(title);
    }

    public EditText getEditText() {
        return mEditText;
    }

}
