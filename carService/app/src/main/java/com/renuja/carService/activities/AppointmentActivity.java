package com.renuja.carService.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.renuja.carService.Database.DBHelperAppointment;
import com.renuja.carService.R;

import java.util.ArrayList;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {

    EditText etProblem, etLocation;
    Spinner visitType;
    int userId, ownerId;
    Button btnSubmitAppointment;
    DBHelperAppointment dbAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        etProblem = findViewById(R.id.etProblem);
        etLocation = findViewById(R.id.etLocation);
        visitType = findViewById(R.id.spVisitType);
        btnSubmitAppointment = findViewById(R.id.btnSubmitAppointment);
        final String[] location = new String[1];
        final String[] problem = new String[1];
        final String[] type = new String[1];

        // Set up Spinner

        List<String> visitTypes = new ArrayList<>();
        visitTypes.add("Pick-up");
        visitTypes.add("Come to the workshop");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                visitTypes
        );
        visitType.setAdapter(adapter);

        // Receive from Intent
        userId = getIntent().getIntExtra("user_id", -1);
        ownerId = getIntent().getIntExtra("owner_id", -1);

        btnSubmitAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userId == -1 || ownerId == -1) {
                    Toast.makeText(v.getContext(), "Invalid user or owner", Toast.LENGTH_SHORT).show();
                    return;
                }

                location[0] = etLocation.getText().toString().trim();
                problem[0] = etProblem.getText().toString().trim();
                type[0] = visitType.getSelectedItem().toString();

                dbAppointment = new DBHelperAppointment(v.getContext());

                boolean success = dbAppointment.registerAppointment(userId, ownerId, problem[0], type[0], location[0]);

                if (success) {
                    Toast.makeText(v.getContext(), "Appointment booked successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Failed to book appointment", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}