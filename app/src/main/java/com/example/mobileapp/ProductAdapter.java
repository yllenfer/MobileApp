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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobileapp.firestore.FirebaseClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {


    private Context context;
    private List<ProductModel> productModelList;
//    Button button;
    ArrayList<ProductModel> originalProduct;


    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
        originalProduct = new ArrayList<>();
        originalProduct.addAll(productModelList);

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
                String product_id = productModelList.get(position).getId();
                Intent intent = new Intent(v.getContext() , Purchase.class);
                intent.putExtra("productID", product_id);
                v.getContext().startActivity(intent);

            }
        });


    }

    public void filter(String findText){
        int sizeOfText = findText.length();
        if(sizeOfText == 0){
            productModelList.clear();
            productModelList.addAll(originalProduct);

        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ProductModel> collection = productModelList.stream()
                        .filter(i -> i.getProduct_name().toLowerCase().contains(findText.toLowerCase()))
                        .collect(Collectors.toList());
                productModelList.clear();
                productModelList.addAll(collection);
            }else{
                for(ProductModel p: originalProduct){
                    if(p.getProduct_name().toLowerCase().contains(findText.toLowerCase())){
                        productModelList.add(p);

                    }
                }
            }
        }
        notifyDataSetChanged();

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


