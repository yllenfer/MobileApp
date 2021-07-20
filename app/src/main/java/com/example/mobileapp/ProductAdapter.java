package com.example.mobileapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.firestore.FirebaseClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context context;
    private List<ProductModel> productModelList;
//    Button button;


    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }


    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(productModelList.get(position).getImage()).into(holder.image);
        holder.name.setText(productModelList.get(position).getProduct_name());
//        holder.description.setText("Description: " + productModelList.get(position).getDescription());
        holder.price.setText("$" + productModelList.get(position).getPrice().toString());
//        holder.quantity.setText("Quantity: " + productModelList.get(position).getQuantity().toString());
        holder.addtocart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //same if I want to erase products set it to false
                FirebaseDatabase.getInstance().getReference().child("products").child(productModelList.get(position).getId()).child("cart").setValue(true);


            }


        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String product_name = productModelList.get(position).getProduct_name();
                Intent intent = new Intent(v.getContext() , Purchase.class);
                intent.putExtra("productName", product_name);
                v.getContext().startActivity(intent);
                System.out.println("testing click on item view " + position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView price;
        TextView description;
        TextView name;
        TextView quantity;
        Button addtocart;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.roundedImage);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.p_description);
            name = itemView.findViewById(R.id.title);
//            quantity = itemView.findViewById(R.id.quantity);
            addtocart = itemView.findViewById(R.id.addtocart);


        }


    }
}


