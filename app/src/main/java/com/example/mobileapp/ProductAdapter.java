package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter <ProductAdapter.ViewHolder> {

    private Context context;
    private List<ProductModel> productModelList;


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
        holder.description.setText(productModelList.get(position).getDescription());
        holder.price.setText(productModelList.get(position).getPrice().toString());
        holder.quantity.setText(productModelList.get(position).getQuantity().toString());



    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }



    public class ViewHolder extends  RecyclerView.ViewHolder{

        ImageView image;
        TextView price;
        TextView description;
        TextView name;
        TextView quantity;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image =itemView.findViewById(R.id.roundedImage);
            price = itemView.findViewById(R.id.price);
            description = itemView.findViewById(R.id.p_description);
            name = itemView.findViewById(R.id.title);
            quantity = itemView.findViewById(R.id.quantity);


        }
    }




}



