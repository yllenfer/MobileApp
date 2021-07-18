package com.example.mobileapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {


    DatabaseReference db;
    FirebaseAuth auth;
    TextView cartTitle;
    RecyclerView recyclerView;
    CartAdapter cartAdapter;
    List<CartModel> cartModelList;
    TextView overTotalAmount;

    public Cart() {

    }


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_fragment);


        db = FirebaseDatabase.getInstance().getReference().child("products");
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        cartModelList = new ArrayList<>();
        cartAdapter = new CartAdapter(this,cartModelList);
        recyclerView.setAdapter(cartAdapter);
        overTotalAmount = findViewById(R.id.total_price);



        // Read from the database
        Query myRef = null;
        db.addValueEventListener(new ValueEventListener() {
            private static final String TAG = "";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot ) {
                cartModelList.clear();


                for (DataSnapshot child : dataSnapshot.getChildren()) {
                     CartModel cartModel = child.getValue(CartModel.class);

                    //This line will go in cart class
                    if (cartModel.cart) {
                        cartModelList.add(cartModel);
                    Toast.makeText(Cart.this, "Product has been added", Toast.LENGTH_SHORT).show();
                    }
                }

                cartAdapter = new CartAdapter(getBaseContext(), cartModelList);
                recyclerView.setAdapter(cartAdapter);
                calculateTotalAmount(cartModelList);




            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }




        });

//        calculateTotalAmount(cartModelList);

    }

    private void calculateTotalAmount(List<CartModel> cartModelList) {

        double totalAmount = 0.0;
        for(CartModel cartModel : cartModelList){
            totalAmount += cartModel.getPrice();
        }

//        return totalAmount;
        overTotalAmount.setText("TOTAL: " + totalAmount );
    }


    public void goToCart(View view) {
        Intent intent  = new Intent(Cart.this, Profile.class);
        startActivity(intent);
        finish();
    }



    public void goToCheckOut(View view) {
        Intent intent  = new Intent(Cart.this, Checkout.class);
        startActivity(intent);
        finish();
    }


    public void goToProfile(View view) {
        Intent intent  = new Intent(Cart.this, Profile.class);
        startActivity(intent);
        finish();
    }

    public void goBackButton(View view) {
//        Intent intent  = new Intent(Cart.this, LogIn.class);
//        startActivity(intent);
//        finish();
        onBackPressed();
    }

    public void goToProduct(View view) {
        Intent intent  = new Intent(Cart.this, Product.class);
        startActivity(intent);
        finish();
    }






}


