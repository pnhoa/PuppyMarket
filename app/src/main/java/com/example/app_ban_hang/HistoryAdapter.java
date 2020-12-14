package com.example.app_ban_hang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.app_ban_hang.model.OrderInformation;


import java.util.ArrayList;
import java.util.Collection;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> implements Filterable {
    private static ArrayList<OrderInformation> mOrder;
    private static ArrayList<OrderInformation>  movelistALl;
    private static Context mContext;
    private static int position;

    public HistoryAdapter(ArrayList<OrderInformation> mOrder, Context mContext) {
        this.mOrder = mOrder;
        this.mContext = mContext;
        this.movelistALl = new ArrayList<>(mOrder);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Glide.with(mContext)
//                .load(mOrder.get(position).getUrl())
//                .into(holder.imageDog);
       // holder.imageDog.setText(mConctact.get(position).getName());
        holder.nameDog.setText(mOrder.get(position).getEmail());
        holder.totalPrice.setText((int) mOrder.get(position).getTotalPayment());
        holder.purchaseTime.setText(mOrder.get(position).getPurchaseTime());
    }


    @Override
    public int getItemCount() {
        return mOrder.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<OrderInformation> filtered = new ArrayList<>();

            if(charSequence.toString().isEmpty()){
                filtered.addAll(movelistALl);
            }
            else{
                for(OrderInformation order: movelistALl){
                    if(order.getUsername().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filtered.add(order);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filtered;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mOrder.clear();
            mOrder.addAll((Collection<? extends OrderInformation>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public View view;
        private TextView nameDog,quantityDog,totalPrice,purchaseTime;
        private LinearLayout item_history;
        private ImageView imageDog;
        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            nameDog = view.findViewById(R.id.nameDog);
            quantityDog = view.findViewById(R.id.quantityDog);
            totalPrice = view.findViewById(R.id.totalPrice);
            purchaseTime = view.findViewById(R.id.purchaseTime);
            imageDog = view.findViewById(R.id.imageDog);
            item_history = view.findViewById(R.id.item_history);
            position = -1;

        }
    }

}
