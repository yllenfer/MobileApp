package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter <CartAdapter.ViewHolder>{

    private Context context;
    private List<CartModel> cartModelList;
    int totalPrice = 0;
    DatabaseReference db;
    FirebaseAuth auth;



    public CartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        auth = FirebaseAuth.getInstance();
    }



    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CartAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(cartModelList.get(position).getImage()).into(holder.image);
        holder.name.setText(cartModelList.get(position).getProduct_name());
        holder.price.setText("$" + cartModelList.get(position).getPrice().toString());
        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                //here is the bug
//                //same if I want to erase products set it to false
//                for(int i = cartModelList.size()-1; i >= 0; i--) {
//                    if(cartModelList.get(i).selected) {
//                        cartModelList.remove(i);
//                    }
//                }
//                notifyDataSetChanged();

                FirebaseDatabase.getInstance().getReference().child("products").child(cartModelList.get(position).getId()).child("cart").setValue(false);
            }


//                public void toggleSelection(int position) {
//                    Product selectedProduct = (Product) getItem(position);
//                    if(selectedProduct.selected) { // no need to check " == true"
//                        selectedProduct.selected = false;
//                    }
//                    else {
//                        selectedProduct.selected = true;
//                    }
//                    notifyDataSetInvalidated();
//                }

//            }


        });



//        totalPrice = totalPrice + cartModelList.get(position).getTotalPrice();
//        Intent intent = new Intent("MytotalAmount");
//        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);


    }




    @Override
    public int getItemCount() {
        return cartModelList.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView image;
        TextView price;
        TextView name;
        ImageView delete;




        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.roundedImage);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.carttitle);
            delete = itemView.findViewById(R.id.delete);



        }
    }




}



