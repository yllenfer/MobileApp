package com.example.mobileapp;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private ImageView addAgeButtonIV;
    private ImageView addPhoneButtonIV;
    private ImageView addAgeCheckIV;
    private ImageView addPhoneCheckIV;
    private TextView ageHintTV;
    private TextView phoneHintTV;
    private EditText ageFieldET;
    private EditText phoneFieldET;
    User userProfile;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth auth;
    private StorageReference storage;
    private CircleImageView profileImage;
    private ImageView addImageBT;
    private String storagePath = "profile_images/*";

    //permissions
    private static final int STORAGE_REQUEST_CODE = 200;
    private static final int IMAGE_SELECTION_CODE = 300;

    //matrixes
    private String [] storagePermissions;
    private Uri image_uri;
    private String profile;

    private Button logOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth  = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        userID = user.getUid();

        final TextView nameTV  = (TextView) findViewById(R.id.profile_name);
        final TextView emailTV  = (TextView) findViewById(R.id.profile_email);

        addAgeButtonIV = findViewById(R.id.imageView20);
        addPhoneButtonIV = findViewById(R.id.imageView15);
        addAgeCheckIV = findViewById(R.id.addAge);
        addPhoneCheckIV = findViewById(R.id.addPhone);
        ageHintTV = findViewById(R.id.profile_age);
        phoneHintTV = findViewById(R.id.profile_number);
        ageFieldET = findViewById(R.id.ageField);
        phoneFieldET = findViewById(R.id.phoneField);

        profileImage = findViewById(R.id.profile_image_view);
        storage = FirebaseStorage.getInstance().getReference();
        storagePermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};

        logOut = findViewById(R.id.btlogout);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    nameTV.setText(userProfile.name + " " + userProfile.lastName);
                    emailTV.setText(userProfile.email);
                    if (userProfile.age.equals("ADD AGE")) {
                        ageHintTV.setText(userProfile.age);
                    } else {
                        ageHintTV.setText(userProfile.age + " years old");
                    }
                    phoneHintTV.setText(userProfile.phone_number);

                    try {
                        Picasso.get().load(userProfile.image).into(profileImage);
                    } catch (Exception e) {
                        Picasso.get().load(R.drawable.profile_photo).into(profileImage);
                    }
                } else {
                    Toast.makeText(Profile.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(Profile.this, "Something wrong happened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onStart() {
        super.onStart();
        if (user != null) {} else {}
    }

    public void addAge(View v) {
        addAgeButtonIV.setVisibility(View.INVISIBLE);
        addAgeCheckIV.setVisibility(View.VISIBLE);
        ageHintTV.setVisibility(View.INVISIBLE);
        ageFieldET.setVisibility(View.VISIBLE);
    }

    public void addPhone(View v){
        addPhoneButtonIV.setVisibility(View.INVISIBLE);
        addPhoneCheckIV.setVisibility(View.VISIBLE);
        phoneHintTV.setVisibility(View.INVISIBLE);
        phoneFieldET.setVisibility(View.VISIBLE);
    }

    public void updateAge(View v) {
        if (!userProfile.age.equals(ageFieldET.getText().toString())) {
            if (!ageFieldET.getText().toString().equals("")) {
                reference.child(userID).child("age").setValue(ageFieldET.getText().toString());
                ageHintTV.setText(ageFieldET.getText().toString() + " years old");
            }
            addAgeCheckIV.setVisibility(View.INVISIBLE);
            addAgeButtonIV.setVisibility(View.VISIBLE);
            ageFieldET.setVisibility(View.INVISIBLE);
            ageHintTV.setVisibility(View.VISIBLE);
        }
    }

    public void updatePhone (View v){
        if (!userProfile.phone_number.equals(phoneFieldET.getText().toString())) {
            if (!phoneFieldET.getText().toString().equals("")) {
                reference.child(userID).child("phone_number").setValue(phoneFieldET.getText().toString());
                phoneHintTV.setText(phoneFieldET.getText().toString());
        }
            addPhoneCheckIV.setVisibility(View.INVISIBLE);
            addPhoneButtonIV.setVisibility(View.    VISIBLE);
            phoneFieldET.setVisibility(View.INVISIBLE);
            phoneHintTV.setVisibility(View.VISIBLE);
        }
    }

    public void updateProfile(View view) {
        profile = "image";
        updatePicture();
    }

    private void updatePicture() {
        String [] options = {"Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select image of: ");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    //gallery selection
                    if (!proveStoragePermission()) {
                        //if permission were not habilitated
                        requestStoragePermission();
                    } else {
                        //is were habilitated
                        chooseImageFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }

    //request permission on runtime
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestStoragePermission() {
        requestPermissions(storagePermissions, STORAGE_REQUEST_CODE);
    }

    //prove is the storage permissions are habilitated or not
    private boolean proveStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(Profile.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    //it's called when user or gamer presses allow or deny dialog square
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_REQUEST_CODE: {
                //gallery selection
                if (grantResults.length > 0) {
                    boolean storageScriptureAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    if (storageScriptureAccepted) {
                        //permission habilitated
                        chooseImageFromGallery();
                    } else {
                        //is permission was denied
                        Toast.makeText(this, "Allow permission of gallery", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //it's called when the user has already chose the image from the gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            //obtain the image uri
            if (requestCode == IMAGE_SELECTION_CODE) {
                image_uri = data.getData();
                uploadPhoto(image_uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //this method changes the user profile picture and updates the database information
    private void uploadPhoto(Uri image_uri) {
        String FileAndNamePath = storagePath + "" + profile + "" + user.getUid();
        StorageReference storageReference = storage.child(FileAndNamePath);
        storageReference.putFile(image_uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isSuccessful());
                        Uri downloadUri = uriTask.getResult();

                        if (uriTask.isSuccessful()) {
                            HashMap<String, Object> result = new HashMap<>();
                            result.put(profile, downloadUri.toString());
                            reference.child(user.getUid()).updateChildren(result)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Profile.this, "Image has been changed correctly.", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull @NotNull Exception e) {
                                    Toast.makeText(Profile.this, "An erro has ocurred.", Toast.LENGTH_SHORT).show();
                                }
                            });

                        } else {
                            Toast.makeText(Profile.this, "Something has gone wrong.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(Profile.this, "Something has gone wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //this method opens gallery
    private void chooseImageFromGallery() {
        Intent IntentGallery = new Intent(Intent.ACTION_PICK);
        IntentGallery.setType("image/*");
        startActivityForResult(IntentGallery, IMAGE_SELECTION_CODE);
    }

    public void goProduct(View view) {
        Intent intent = new Intent(this, Product.class);
        startActivity(intent);
        finish();
    }

    public void changePassword(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        LayoutInflater inflater = getLayoutInflater();
        View vw = inflater.inflate(R.layout.dialog_update_password, null);
        builder.setView(vw);

        AlertDialog dialog = builder.create();
        dialog.show();
        EditText oldPassword = vw.findViewById(R.id.etoldpassword);
        EditText newPassword = vw.findViewById(R.id.etnewpassword);
        Button updatePassword = vw.findViewById(R.id.btupdatepassword);
        Button cancelPassword = vw.findViewById(R.id.btcancelpassword);

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPass = oldPassword.getText().toString();
                String newPass = newPassword.getText().toString();
                if (TextUtils.isEmpty(oldPass)) {
                    Toast.makeText(Profile.this, "Enter current password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (newPassword.length()<6) {
                    Toast.makeText(Profile.this, "Password must be al least 6 characters long.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dialog.dismiss();
                updatePass(oldPass, newPass);
            }
        });

        cancelPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void updatePass(String oldPass, String newPass) {
        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), oldPass);
        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                user.updatePassword(newPass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Profile.this, "Password updated.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(Profile.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(Profile.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToCart(View view) {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
        finish();
    }

    public void logOut(View view) {
        auth.signOut();
        Toast.makeText(Profile.this, "Goodbye " + userProfile.email, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Profile.this, Product.class);
        startActivity(intent);
        finish();
    }

    public void goNotifications(View view) {
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
        finish();
    }
}