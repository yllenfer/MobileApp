package com.example.mobileapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class TCTextViewBold extends AppCompatTextView {

    public TCTextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TCTextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TCTextViewBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "publicsans_bold.otf");
        setTypeface(tf);

    }
}