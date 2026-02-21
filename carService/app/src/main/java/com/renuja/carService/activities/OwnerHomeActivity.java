package com.renuja.carService.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.Database.DBHelperAppointment;
import com.renuja.carService.Database.DBHelperOwner;
import com.renuja.carService.R;
import com.renuja.carService.adapters.AppointmentsAdapter;
import com.renuja.carService.models.Appointment;
import com.renuja.carService.models.Owner;

import java.util.List;

public class OwnerHomeActivity extends AppCompatActivity {

    DBHelperOwner dbOwner;
    DBHelperAppointment dbAppointment;

    TextView tvName, tvType, tvAddress;
    RecyclerView rvAppointments;

    AppointmentsAdapter adapter;
    List<Appointment> appointmentList;

    int ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        tvName = findViewById(R.id.tvOwnerBusinessName);
        tvType = findViewById(R.id.tvOwnerBusinessType);
        tvAddress = findViewById(R.id.tvOwnerBusinessLocation);
        rvAppointments = findViewById(R.id.rvOwnerAppointments);

        dbOwner = new DBHelperOwner(this);
        dbAppointment = new DBHelperAppointment(this);

        ownerId = getIntent().getIntExtra("OWNER_ID", -1);

        if (ownerId != -1) {
            loadProviderDetails(ownerId);
            loadAppointments(ownerId);
        }

        rvAppointments.setLayoutManager(new LinearLayoutManager(this));
    }

    // ✅ LOAD APPOINTMENTS
    private void loadAppointments(int ownerId) {

        appointmentList = dbAppointment.getAppointmentsWithUser(ownerId);

        adapter = new AppointmentsAdapter(
                this,
                appointmentList,
                new AppointmentsAdapter.OnAppointmentActionListener() {

                    @Override
                    public void onApprove(Appointment model, int position) {

                        dbAppointment.updateAppointmentStatus(
                                model.getId(),
                                "approved"
                        );

                        adapter.removeItem(position);
                    }

                    @Override
                    public void onCancel(Appointment model, int position) {

                        dbAppointment.updateAppointmentStatus(
                                model.getId(),
                                "cancelled"
                        );

                        adapter.removeItem(position);
                    }

                    @Override
                    public void onItemClick(Appointment model) {
                        // optional → open full details
                    }
                });

        rvAppointments.setAdapter(adapter);
    }

    // LOAD OWNER DETAILS
    private void loadProviderDetails(int ownerId) {

        Owner owner = dbOwner.getOwnerByRegId(ownerId);

        if (owner == null) return;

        tvName.setText(Owner.getBusinessName());
        tvType.setText(owner.getBusinessType());
        tvAddress.setText(Owner.getAddress());
    }
}
