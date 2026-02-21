package com.renuja.carService.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.R;
import com.renuja.carService.activities.ProviderDetailActivity;
import com.renuja.carService.models.Owner;

import java.util.List;

public class ProviderAdapter extends RecyclerView.Adapter<ProviderAdapter.ViewHolder> {

    private List<Owner> ownerList;
    private Context context;
    private int userId;

    public ProviderAdapter(List<Owner> ownerList, int userId) {
        this.context = context;
        this.ownerList = ownerList;
        this.userId = userId;
    }

    @NonNull
    @Override
    public ProviderAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_provider, parent, false);
        return new ProviderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Owner owner = ownerList.get(position);
        holder.tvProviderName.setText(owner.getBusinessName());
        holder.tvProviderPhone.setText(owner.getPhone());
        holder.tvProviderAddress.setText(owner.getAddress());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProviderDetailActivity.class);
            intent.putExtra("owner_id", owner.getId());
            intent.putExtra("user_id", userId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return ownerList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProviderName;
        TextView tvProviderPhone;
        TextView tvProviderAddress;

        ViewHolder(View itemView) {
            super(itemView);
            tvProviderName = itemView.findViewById(R.id.tvProviderName);
            tvProviderPhone = itemView.findViewById(R.id.tvProviderPhone);
            tvProviderAddress = itemView.findViewById(R.id.tvProviderAddress);
        }
    }

}
