package com.example.mobileapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TCTextView extends AppCompatTextView {

    public TCTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TCTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TCTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "publicsans_regular.otf");
        setTypeface(tf);

    }
}