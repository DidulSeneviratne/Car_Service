package com.renuja.carService.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.renuja.carService.Database.DBHelperReview;
import com.renuja.carService.R;

public class ReviewActivity extends AppCompatActivity {
    EditText etReview;
    Button btnSubmitReview;
    DBHelperReview dbHelperReview;
    int userId, ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        etReview = findViewById(R.id.etReview);
        btnSubmitReview = findViewById(R.id.btnSubmitReview);

        dbHelperReview = new DBHelperReview(this);

        // Receive from Intent
        userId = getIntent().getIntExtra("user_id", -1);
        ownerId = getIntent().getIntExtra("owner_id", -1);

        if (userId == -1 || ownerId == -1) {
            Toast.makeText(this, "Invalid user or owner", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnSubmitReview.setOnClickListener(v -> submitReview());
    }

    private void submitReview() {
        String reviewText = etReview.getText().toString().trim();

        if (reviewText.isEmpty()) {
            etReview.setError("Please enter a review");
            etReview.requestFocus();
            return;
        }

        boolean success = dbHelperReview.registerReview(
                String.valueOf(userId),
                String.valueOf(ownerId),
                reviewText
        );

        if (success) {
            Toast.makeText(this, "Review submitted successfully!", Toast.LENGTH_SHORT).show();
            etReview.setText("");   // clear box
            finish();               // optional: go back
        } else {
            Toast.makeText(this, "Failed to submit review", Toast.LENGTH_SHORT).show();
        }
    }

}