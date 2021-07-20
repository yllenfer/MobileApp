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
        Intent i = getIntent();
        String productName = i.getStringExtra("productName");
        System.out.println(productName);

        if (getIntent().hasExtra("productName")) {
            product_id = getIntent().getStringExtra("productName");
            Log.d("product id: ", product_id);
        }

//        shoppingCartImage.setOnClickListener(v -> {
//            startActivity(new Intent(Purchase.this, Cart.class));
//            finish();
//        });

        commentSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCommentToFirebase();
            }
        });


    }

    public void addProductsToView(ProductModel productModel) {


    }

    private void saveCommentToFirebase() {
        EditText commentInput = findViewById(R.id.comment_input);

        CommentModel commentModel = new CommentModel(commentInput.getText().toString(),
                FirebaseClass.getUserID());

        FirebaseClass.addMessage(this, commentModel);

    }


    public void goToNotifications(View view) {
        Intent intent = new Intent(Purchase.this, Notifications.class);
        startActivity(intent);
        finish();
    }


    public void goToProfile(View view) {
        Intent intent = new Intent(Purchase.this, Profile.class);
        startActivity(intent);
        finish();
    }


    public void goToCart(View view) {
        Intent intent = new Intent(Purchase.this, Cart.class);
        startActivity(intent);
        finish();
    }

    public void goToProduct(View view) {
        Intent intent = new Intent(Purchase.this, Product.class);
        startActivity(intent);
        finish();
    }

    public void goToCheckOut(View view) {
        Intent intent = new Intent(Purchase.this, Checkout.class);
        startActivity(intent);
        finish();
    }

}