package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileapp.firestore.FirebaseClass;
import com.example.mobileapp.utils.TCTextView;

import java.util.List;
import java.util.Map;

public class Overview extends AppCompatActivity {
    public String productName = "";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        Button completeOrderBtn = findViewById(R.id.completeOrder);
        View backButton = findViewById(R.id.back_button);
        getAddressFireBase();

        List<CartModel> cartList = Cart.getInstance().getList();


        StringBuilder productsList = new StringBuilder();

        int i = 1;
        float total = 0;
        for (CartModel cartModel: cartList) {
            System.out.println(cartModel);
            productsList.append(i + " " + cartModel.getProduct_name() + " " + "$"+ cartModel.getPrice() + " \n");
            total += cartModel.getPrice();
        }



        TextView productBox = findViewById(R.id.products_box);
        TextView totalView = findViewById(R.id.total);

        productBox.setText(productsList);
        totalView.setText("Total: " + total);


        completeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Overview.this, "orden completado", Toast.LENGTH_LONG).show();

            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            //created function to have the imageView go back to desired activity
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Overview.this, Checkout.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void getAddressFireBase() {
        FirebaseClass.getAddress(this);
    }

    public void setAddressList(Map<String, String> addressList) {
        //sets address to View
        TCTextView addressInfo = findViewById(R.id.billing_info);
        String result = "Address: " + addressList.get("address") +"\n" +
                "State: " + addressList.get("state") + "\n" +
                "Postal Code: " + addressList.get("postalCode");

        addressInfo.setText(result);

    }


}