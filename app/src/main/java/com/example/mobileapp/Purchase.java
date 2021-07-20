package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.models.CommentModel;
import com.example.mobileapp.models.MessagesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("products").child(productName);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ProductModel product = snapshot.getValue(ProductModel.class);
                TextView description = findViewById(R.id.description);
                ImageView image = findViewById(R.id.productImage);
                TextView title = findViewById(R.id.titleProduct);
                TextView price = findViewById(R.id.product_price);

                description.setText(product.getDescription());
                Glide.with(Purchase.this).load(product.getImage()).into(image);
                title.setText(product.getProduct_name());
                price.setText("$" + product.getPrice().toString());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        commentSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText commentInput = findViewById(R.id.comment_input);

                if (commentInput.getText().toString().equals("")){
                    Toast.makeText(Purchase.this, "no comment sent", Toast.LENGTH_LONG).show();
                } else {
                    FirebaseUser userFirebase = FirebaseAuth.getInstance().getCurrentUser();
                    User user = new User(userFirebase.getEmail(), FirebaseClass.getUserID());

                    String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                    MessagesModel messagesModel = new MessagesModel(commentInput.getText().toString(), user , timeStamp);

                    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("messages").child(timeStamp).push();
                    db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            db.setValue(messagesModel);

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                    commentInput.setText("");

                }
            }
        });

    }


    public void goToNotifications(View view) {
        Intent intent = new Intent(Purchase.this, Notifications.class);
        startActivity(intent);
    }


    public void goToProfile(View view) {
        Intent intent = new Intent(Purchase.this, Profile.class);
        startActivity(intent);

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
    }

}