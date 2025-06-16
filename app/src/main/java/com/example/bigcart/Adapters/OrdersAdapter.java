package com.example.bigcart.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bigcart.Models.Order;
import com.example.bigcart.R;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrdersAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
    Order order = orderList.get(position);
    holder.nameorder.setText(order.getName());
    holder.orderdate.setText(order.getOrder_date());
    holder.price.setText(order.getOrder_total());
    holder.status.setText(order.getStatus().getStatus_name());
    holder.datedeliver.setText(order.getDelivery_date());
    }
    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameorder, orderdate, price, status, datedeliver;
        ImageView prodimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameorder = itemView.findViewById(R.id.nameorder);
            orderdate = itemView.findViewById(R.id.orderdate);
            price = itemView.findViewById(R.id.price);
            status = itemView.findViewById(R.id.status);
            datedeliver = itemView.findViewById(R.id.datedeliver);
            prodimage = itemView.findViewById(R.id.prodimage);
        }
    }
}
