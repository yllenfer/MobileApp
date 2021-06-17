package com.example.mobileapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class TCToolbarTitle extends AppCompatTextView {

    public TCToolbarTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TCToolbarTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TCToolbarTitle(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "azonix.otf");
        setTypeface(tf);

    }
}