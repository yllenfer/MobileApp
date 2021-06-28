package com.example.mobileapp.firestore;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mobileapp.AddProduct;
import com.example.mobileapp.models.ProductModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;


public class FirestoreClass {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void registerProduct(AddProduct addProduct, ProductModel productInfo) {

        db.collection("products")
                .document(productInfo.getProduct_name())
                .set(productInfo, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(FirestoreClass.class.toString(), "Document added");
                        Toast.makeText(addProduct, "product added succesfully", Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(FirestoreClass.class.toString(), "Error adding document", e);
                        Toast.makeText(addProduct, "process failed", Toast.LENGTH_LONG).show();
                    }
                });

    }


}
