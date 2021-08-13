package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView productRecycler;
    List<ProductModel> productModelList;
    ProductAdapter productAdapter;
    DatabaseReference db;
    FirebaseUser user;
    SearchView search;
//    RoundedImageView imageView;


    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        user = FirebaseAuth.getInstance().getCurrentUser();

        setContentView(R.layout.fragment_home);
        db = FirebaseDatabase.getInstance().getReference().child("products");
//      Button bnt = findViewById(R.id.addtocart);

        productRecycler = findViewById(R.id.recyclerView);
        search = findViewById(R.id.search_bar);
        productRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        productModelList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productModelList);
        productRecycler.setAdapter(productAdapter);
//        imageView = findViewById(R.id.roundedImage);
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent  = new Intent(Product.this, Purchase.class);
//                startActivity(intent);
//                finish();
//
//            }
//        });

        search.setOnQueryTextListener(this);

        Query myRef;
        db.addValueEventListener(new ValueEventListener() {
            private static final String TAG = "";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productModelList.clear();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    ProductModel productModel = child.getValue(ProductModel.class);
                    //This line will go in cart class
//                    if (productModel.cart)
                    productModelList.add(productModel);
//                    Toast.makeText(Product.this, "added", Toast.LENGTH_SHORT).show();
                }

                productAdapter = new ProductAdapter(getBaseContext(), productModelList);
                productRecycler.setAdapter(productAdapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


//     bnt.setOnClickListener(new View.OnClickListener() {
//
//         @Override
//         public void onClick(View v) {
//
//
//         }
//     });


    }


    public void goToCart(View view) {
        if (user != null) {
            Intent intent = new Intent(Product.this, Cart.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(Product.this, LogIn.class);
            startActivity(intent);

        }
    }


    public void goToProfile(View view) {
        if (user != null) {
            Intent intent = new Intent(Product.this, Profile.class);
            startActivity(intent);


        } else {
            Intent intent = new Intent(Product.this, LogIn.class);
            startActivity(intent);


        }


    }

    public void goToNotifications(View view) {
        Intent intent = new Intent(Product.this, Notifications.class);
        startActivity(intent);
    }


    public void goToPurchase(View view) {
        Intent intent = new Intent(Product.this, LogIn.class);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        productAdapter.filter(newText);
        return false;
    }
}
