package com.example.mobileapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class TCEditTextTitle extends AppCompatEditText {

    public TCEditTextTitle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TCEditTextTitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TCEditTextTitle(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "azonix.otf");
        setTypeface(tf);

    }
}