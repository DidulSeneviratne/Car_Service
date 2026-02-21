package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.renuja.carService.Database.DBHelperAppointment;
import com.renuja.carService.R;
import com.renuja.carService.adapters.UserAppointmentsAdapter;
import com.renuja.carService.models.UserAppointment;

import java.util.List;

public class UserCategoriesActivity extends AppCompatActivity {

    LinearLayout mGarage;
    DBHelperAppointment dbHelper;
    RecyclerView recyclerView;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_categories);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        mGarage = findViewById(R.id.m_garage);

        mGarage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UserCategoriesActivity.this, ProviderListActivity.class);
                intent1.putExtra("category", "Mechanical");
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });

        dbHelper = new DBHelperAppointment(this);
        List<UserAppointment> list = dbHelper.getUserAppointments(id);

        recyclerView = findViewById(R.id.rvUserAppointments);
        emptyView = findViewById(R.id.tvEmptyAppointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        UserAppointmentsAdapter adapter =
                new UserAppointmentsAdapter(this, list);

        if (list.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        recyclerView.setAdapter(adapter);
    }
}