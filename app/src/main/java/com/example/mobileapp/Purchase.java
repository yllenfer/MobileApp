package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.CommentModel;

public class Purchase extends AppCompatActivity {

    private String product_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        Button btnBuyNow = findViewById(R.id.buy_now_button);
        ImageView shoppingCartImage = findViewById(R.id.shopping_cart_image);
        View commentSendBtn = findViewById(R.id.comment_send_btn);


        if (getIntent().hasExtra("product_id")) {
            product_id = getIntent().getStringExtra("product_id");
            Log.d("product id: ", product_id);
        }

        //if (getIntent().getStringExtra())

        btnBuyNow.setOnClickListener(v -> {
            startActivity(new Intent(Purchase.this, Product.class));
            finish();
        });

        shoppingCartImage.setOnClickListener(v -> {
            startActivity(new Intent(Purchase.this, Cart.class));
            finish();
        });

        commentSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCommentToFirebase();
            }
        });

    }

    public void addProductsToView(Member member) {



    }


    private void saveCommentToFirebase() {
        EditText commentInput = findViewById(R.id.comment_input);

        CommentModel commentModel = new CommentModel(commentInput.getText().toString(),
                FirebaseClass.getUserID());

        FirebaseClass.addMessage(this, commentModel);

    }




}