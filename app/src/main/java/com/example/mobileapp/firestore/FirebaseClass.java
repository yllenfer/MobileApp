package com.example.mobileapp.firestore;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mobileapp.AddProduct;
import com.example.mobileapp.models.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.SetOptions;

import org.jetbrains.annotations.NotNull;


public class FirebaseClass {


    public void registerProduct(AddProduct addProduct, ProductModel productInfo) {

        DatabaseReference db;

        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                db.child("products").child(productInfo.getProduct_name()).setValue(productInfo);
                Toast.makeText(addProduct, "product added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(addProduct, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });


//        db.collection("products")
//                .document(productInfo.getProduct_name())
//                .set(productInfo, SetOptions.merge())
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.d(FirestoreClass.class.toString(), "Document added");
//                        Toast.makeText(addProduct, "product added succesfully", Toast.LENGTH_LONG).show();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(FirestoreClass.class.toString(), "Error adding document", e);
//                        Toast.makeText(addProduct, "process failed", Toast.LENGTH_LONG).show();
//                    }
//                });

    }


}
