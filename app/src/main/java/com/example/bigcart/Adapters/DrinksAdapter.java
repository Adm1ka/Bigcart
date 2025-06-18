package com.example.bigcart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bigcart.Models.Product;
import com.example.bigcart.R;

import java.util.List;

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder>{
    private Context context;
    private List<Product> productList;
    public DrinksAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }
    @NonNull
    @Override
    public DrinksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new DrinksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksAdapter.ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.prodcost.setText(product.getProduct_price());
        holder.prodname.setText(product.getProduct_name());
        holder.prodweight.setText(product.getProduct_weight());
        String url = "https://simttaxqfqsbjkqhwtre.supabase.co/storage/v1/object/public/imageproduct/";
        Glide.with(context)
                .load(url + product.getProduct_image_url())
                .into(holder.prodimage);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView prodcost, prodname, prodweight;
        ImageView prodimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            prodcost = itemView.findViewById(R.id.prodcost);
            prodname = itemView.findViewById(R.id.prodname);
            prodweight = itemView.findViewById(R.id.prodweight);
            prodimage = itemView.findViewById(R.id.prodimage);
        }
    }
}
