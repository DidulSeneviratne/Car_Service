package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.renuja.carService.Database.DBHelperAppointment;
import com.renuja.carService.Database.DBHelperOwner;
import com.renuja.carService.R;
import com.renuja.carService.models.Owner;

public class ProviderDetailActivity extends AppCompatActivity {

    ImageView imgProviderProfile;
    TextView tvName, tvType, tvAddress, tvPhone, tvDescription;
    RatingBar ratingBar;
    Button btnAppointment, btnReview;
    DBHelperOwner dbOwner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_detail);
        Intent intent = getIntent();
        int userId = intent.getIntExtra("user_id", -1);

        dbOwner = new DBHelperOwner(this);

        // Get ID from Intent
        int ownerId = getIntent().getIntExtra("owner_id", -1);

        imgProviderProfile = findViewById(R.id.imgProviderProfile);
        tvName = findViewById(R.id.tvProviderNameDetail);
        tvType = findViewById(R.id.tvProviderTypeDetail);
        tvAddress = findViewById(R.id.tvProviderAddressDetail);
        tvPhone = findViewById(R.id.tvProviderPhoneDetail);
        tvDescription = findViewById(R.id.tvProviderDescription);
        ratingBar = findViewById(R.id.ratingProviderDetail);
        btnAppointment = findViewById(R.id.btnAppointment);
        btnReview = findViewById(R.id.btnReview);

        if (ownerId != -1) {
            loadProviderDetails(ownerId);
        }

        btnAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AppointmentActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("owner_id", ownerId);
                v.getContext().startActivity(intent);
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReviewActivity.class);
                intent.putExtra("user_id", userId);
                intent.putExtra("owner_id", ownerId);
                v.getContext().startActivity(intent);
            }
        });

    }

    private void loadProviderDetails(int ownerId) {
        Owner owner = dbOwner.getOwnerByRegId(ownerId);

        if (owner == null) return;

        tvName.setText(Owner.getBusinessName());
        tvType.setText(owner.getBusinessType());     // garage / electrician / etc
        tvAddress.setText(Owner.getAddress());
        tvPhone.setText(Owner.getPhone());
        tvDescription.setText(owner.getDescription());
        ratingBar.setRating(owner.getRating());
    }
}