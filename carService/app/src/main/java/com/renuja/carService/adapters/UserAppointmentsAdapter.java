package com.renuja.carService.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.R;
import com.renuja.carService.models.UserAppointment;

import java.util.List;

public class UserAppointmentsAdapter
        extends RecyclerView.Adapter<UserAppointmentsAdapter.ViewHolder> {

    private Context context;
    private List<UserAppointment> list;

    public UserAppointmentsAdapter(Context context, List<UserAppointment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.user_appointments, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserAppointment model = list.get(position);

        holder.tvGarageName.setText(model.businessName);
        holder.tvGarageAddress.setText(model.businessAddress);
        holder.tvGaragePhone.setText(model.businessPhone);

        holder.tvProblem.setText("Problem: " + model.problem);
        holder.tvVisitType.setText("Visit: " + model.visitType);
        holder.tvLocation.setText("Location: " + model.location);

        holder.tvStatus.setText(model.status.toUpperCase());

        // 🎨 Status color
        switch (model.status.toLowerCase()) {

            case "approved":
                holder.tvStatus.setTextColor(Color.GREEN);
                break;

            case "cancelled":
                holder.tvStatus.setTextColor(Color.RED);
                break;

            default:
                holder.tvStatus.setTextColor(Color.GRAY);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvGarageName, tvGarageAddress, tvGaragePhone;
        TextView tvProblem, tvVisitType, tvLocation, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGarageName = itemView.findViewById(R.id.tvGarageName);
            tvGarageAddress = itemView.findViewById(R.id.tvGarageAddress);
            tvGaragePhone = itemView.findViewById(R.id.tvGaragePhone);
            tvProblem = itemView.findViewById(R.id.tvProblem);
            tvVisitType = itemView.findViewById(R.id.tvVisitType);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}