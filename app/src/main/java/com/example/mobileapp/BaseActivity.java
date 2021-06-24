package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class BaseActivity extends AppCompatActivity {
    //BaseActivity class can be used for every layout activity to validate their values

    public void showErrorSnackBar(String message, Boolean errorMessage) {
        //Function used to make snack bar and place it in the the activity layout.
        //if error snack bar changes to the error color else it will change to the success color.

        /*snackbar receives and id make sure to add and id of 'content_layout' in your constraint layout to make
        snack bar work*/
        Snackbar snackbar = Snackbar.make(findViewById(R.id.content_layout), message, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();

        if (errorMessage) {
            snackbarView.setBackgroundColor(
                    ContextCompat.getColor(
                            BaseActivity.this,
                            R.color.snackbar_error
                    )
            );
        } else {
            snackbarView.setBackgroundColor(
                    ContextCompat.getColor(
                            BaseActivity.this,
                            R.color.snackbar_success
                    )
            );
        }
        snackbar.show();
    }
}