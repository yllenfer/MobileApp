package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Purchase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Button btnBuyNow = findViewById(R.id.buy_now_button);
        ImageView shoppingCartImage = findViewById(R.id.shopping_cart_image);

        btnBuyNow.setOnClickListener(v -> {
            startActivity(new Intent(Purchase.this, Checkout.class));
            finish();
        });

        shoppingCartImage.setOnClickListener(v -> {
            startActivity(new Intent(Purchase.this, cartActivity.class));
            finish();
        });

    }


}