package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mobileapp.models.ProductModel;
import com.example.mobileapp.utils.Constants;

public class AddProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Button btn = findViewById(R.id.buttonAddProduct);
        EditText name = findViewById(R.id.p_name);
        EditText price = findViewById(R.id.p_price);
        EditText quantity = findViewById(R.id.p_quantity);
        EditText description = findViewById(R.id.p_description);

        ImageView productImage = findViewById(R.id.p_addImage);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    String name_p = name.getText().toString();
                    float price_p = Float.parseFloat(price.getText().toString());
                    int quantity_p = Integer.parseInt(quantity.getText().toString());
                    String description_p = description.getText().toString();

                    ProductModel productModel = new ProductModel(name_p, price_p, quantity_p, description_p);
                    FirebaseClass firebaseClass = new FirebaseClass();
                    firebaseClass.registerProduct(AddProduct.this, productModel);

                } catch (Exception e) {
                    Toast.makeText(AddProduct.this, "process failed fill everything out", Toast.LENGTH_LONG).show();
                }

            }
        });

        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AddProduct.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(AddProduct.this, "already has permision", Toast.LENGTH_LONG).show();

                } else {
                    ActivityCompat.requestPermissions(
                            AddProduct.this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                            Constants.READ_STORAGE_PERMISSION_CODE
                    );
                }
            }
        });
    }
}