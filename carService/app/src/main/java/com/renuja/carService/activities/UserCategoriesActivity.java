package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.renuja.carService.R;

public class UserCategoriesActivity extends AppCompatActivity {

    LinearLayout mGarage;

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
    }
}