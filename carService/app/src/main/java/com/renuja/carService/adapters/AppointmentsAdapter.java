package com.renuja.carService.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.R;
import com.renuja.carService.models.Appointment;

import java.util.List;

public class AppointmentsAdapter
        extends RecyclerView.Adapter<AppointmentsAdapter.ViewHolder> {

    private Context context;
    private List<Appointment> list;
    private OnAppointmentActionListener listener;

    public AppointmentsAdapter(Context context,
                               List<Appointment> list,
                               OnAppointmentActionListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public AppointmentsAdapter(List<Appointment> appointments) {
        this.list = appointments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_owner_appoinment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Appointment model = list.get(position);

        holder.tvUserName.setText("User: " + model.getName());
        holder.tvUserPhone.setText("Phone: " + model.getPhone());
        holder.tvUserLocation.setText("Location: " + model.getLocation());
        holder.tvProblem.setText("Problem: " + model.getProblem());
        holder.tvVisitType.setText("Visit Type: " + model.getVisitType());

        // APPROVE CLICK
        holder.btnApprove.setOnClickListener(v -> {
            if (listener != null) {
                listener.onApprove(model, position);
            }
        });

        // CANCEL CLICK
        holder.btnCancel.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCancel(model, position);
            }
        });

        // ROW CLICK
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // 🔥 Remove item after DB update (call from Activity)
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }

    // 🔥 ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvUserName, tvUserPhone, tvUserLocation, tvProblem, tvVisitType;
        Button btnApprove, btnCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUserName = itemView.findViewById(R.id.tvUserNameAppointmentOwner);
            tvUserPhone = itemView.findViewById(R.id.tvUserPhoneAppointmentOwner);
            tvUserLocation = itemView.findViewById(R.id.tvUserLocationAppointmentOwner);
            tvProblem = itemView.findViewById(R.id.tvProblemOwner);
            tvVisitType = itemView.findViewById(R.id.tvVisitTypeOwner);

            btnApprove = itemView.findViewById(R.id.btnApproveAppointment);
            btnCancel = itemView.findViewById(R.id.btnRejectAppointment);
        }
    }

    // 🔥 Listener
    public interface OnAppointmentActionListener {
        void onApprove(Appointment model, int position);
        void onCancel(Appointment model, int position);
        void onItemClick(Appointment model);
    }
}