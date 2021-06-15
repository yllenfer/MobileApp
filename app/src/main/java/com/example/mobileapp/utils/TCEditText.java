package com.example.mobileapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class TCEditText extends AppCompatEditText {

    public TCEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TCEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TCEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "publicsans_regular.otf");
        setTypeface(tf);

    }
}
