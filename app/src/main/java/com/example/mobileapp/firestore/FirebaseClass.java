package com.example.mobileapp.firestore;



import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.mobileapp.AddProduct;
import com.example.mobileapp.Chat;
import com.example.mobileapp.Checkout;

import com.example.mobileapp.Notifications;
import com.example.mobileapp.Overview;

import com.example.mobileapp.ProductModel;
import com.example.mobileapp.Purchase;
import com.example.mobileapp.User;
import com.example.mobileapp.models.AddressModel;
import com.example.mobileapp.models.CommentModel;

import com.example.mobileapp.models.MessagesModel;
import com.example.mobileapp.models.SecondProductClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FirebaseClass {
    private static DatabaseReference db;

    public static void registerProduct(AddProduct addProduct, SecondProductClass secondProductClass) {
        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                db.child("products").child(secondProductClass.getProduct_name()).setValue(secondProductClass);
                Toast.makeText(addProduct, "product added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(addProduct, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });

    }

    public static void addAddress(Checkout checkout, AddressModel address) {
        db = FirebaseDatabase.getInstance().getReference().child("Users").child(address.getUser_id());

        Map<String, Object> addressInfo = new HashMap<>();
        addressInfo.put("address", address.getAddress());
        addressInfo.put("postalCode", address.getPostal_code());
        addressInfo.put("state", address.getState());

        db.updateChildren(addressInfo);

    }

    public static void getAddress(Overview overview) {
        db = FirebaseDatabase.getInstance().getReference("Users/" + getUserID());
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                Map<String, String> addressList = new HashMap<>();
                addressList.put("address", dataSnapshot.child("address").getValue().toString());
                addressList.put("postalCode", dataSnapshot.child("postalCode").getValue().toString());
                addressList.put("state", dataSnapshot.child("state").getValue().toString());
                overview.setAddressList(addressList);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }

    public static String getUserID() {
        String userID = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        assert user != null;
        userID = user.getUid();

        return userID;

    }

    public static void setMessageData(MessagesModel messagesModel) {
        String date = messagesModel.getCreatedAt();
        db = FirebaseDatabase.getInstance().getReference("messages").child(date).push();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                db.setValue(messagesModel);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public static void getMessageData(Chat chat, String dateSent) {
        db = FirebaseDatabase.getInstance().getReference("messages").child(dateSent);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                for (DataSnapshot randomNumber : snapshot.getChildren()) {
                    User user = randomNumber.child("sender").getValue(User.class);
                    if (user.getUserId().equals(FirebaseClass.getUserID())) {
                        MessagesModel message = randomNumber.getValue(MessagesModel.class);
                        User userFireBase = randomNumber.child("sender").getValue(User.class);
                        chat.createList(message.getMessage(), userFireBase, message.getCreatedAt());

                    } //add condition for host account.
                }

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

    public static void getNotificationData(Notifications notifications) {
        db = FirebaseDatabase.getInstance().getReference("messages");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String dateTitle = "";
                for (DataSnapshot date : snapshot.getChildren()) {
                    for (DataSnapshot random : date.getChildren()) {
                        User user = random.child("sender").getValue(User.class);
                        if (user.getUserId().equals(FirebaseClass.getUserID())) {
                            if (!dateTitle.equals(random.getValue(MessagesModel.class).getCreatedAt())) {
                                dateTitle = random.getValue(MessagesModel.class).getCreatedAt();
                                notifications.createList(dateTitle);

                            }

                        }
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


    }
}





