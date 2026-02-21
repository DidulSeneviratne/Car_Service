package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.renuja.carService.Database.DBHelperOwner;
import com.renuja.carService.Database.DBHelperUser;
import com.renuja.carService.R;

public class RegisterActivity extends AppCompatActivity {
    String role;
    RadioGroup rgRole;
    LinearLayout layoutUserFields, layoutOwnerFields;
    TextInputEditText etName, etEmailReg, etPhoneReg, etPasswordReg, etVehicleModel, etVehicleType, etBusinessName, etBusinessRegNo, etBusinessType,
            etBusinessDescription, etBusinessAddress, etBusinessPhone;
    RadioButton rbUser, rbOwner;
    Button btnRegister;
    DBHelperUser db;
    DBHelperOwner db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DBHelperUser(this);
        db1 = new DBHelperOwner(this);

        Intent intent = getIntent();
        role = intent.getStringExtra("role");

        rgRole.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rbUser) {
                layoutUserFields.setVisibility(View.VISIBLE);
                layoutOwnerFields.setVisibility(View.GONE);
            } else if (checkedId == R.id.rbOwner) {
                layoutUserFields.setVisibility(View.GONE);
                layoutOwnerFields.setVisibility(View.VISIBLE);
                MaterialAutoCompleteTextView businessType =
                        findViewById(R.id.etBusinessType);

                String[] businessTypes = {
                        "Mechanical Garage",
                        "Electrical Garage",
                        "Battery Shops",
                        "Service Centers",
                        "Vehicle Carriers",
                        "Spare Parts Shops",
                        "Bicycle / Three-wheel Service Centers",
                        "Tinkering & Painting Services"
                };

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_dropdown_item_1line,
                        businessTypes
                );

                businessType.setAdapter(adapter);
            }
        });

        bindViews();
        setupRegisterButton();
    }

    private void bindViews() {
        etName = findViewById(R.id.etName);
        etEmailReg = findViewById(R.id.etEmailReg);
        etPhoneReg = findViewById(R.id.etPhoneReg);
        etPasswordReg = findViewById(R.id.etPasswordReg);

        rgRole = findViewById(R.id.rgRole);
        rbUser = findViewById(R.id.rbUser);
        rbOwner = findViewById(R.id.rbOwner);

        layoutUserFields = findViewById(R.id.layoutUserFields);
        layoutOwnerFields = findViewById(R.id.layoutOwnerFields);

        etVehicleModel = findViewById(R.id.etVehicleModel);
        etVehicleType = findViewById(R.id.etVehicleType);

        etBusinessRegNo = findViewById(R.id.etBusinessRegNo);
        etBusinessName = findViewById(R.id.etBusinessName);
        etBusinessType = findViewById(R.id.etBusinessType);
        etBusinessDescription = findViewById(R.id.etBusinessDescription);
        etBusinessAddress = findViewById(R.id.etBusinessAddress);
        etBusinessPhone = findViewById(R.id.etBusinessPhone);

        btnRegister = findViewById(R.id.btnRegister);
    }

    private void setupRegisterButton() {
        btnRegister.setOnClickListener(v -> {

            String Name = etName.getText().toString().trim();
            String email = etEmailReg.getText().toString().trim();
            String phone = etPhoneReg.getText().toString().trim();
            String password = etPasswordReg.getText().toString().trim();

            if (Name.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (rbUser.isChecked()) {
                String vehicleModel = etVehicleModel.getText().toString().trim();
                String vehicleType = etVehicleType.getText().toString().trim();

                if(vehicleModel.isEmpty() || vehicleType.isEmpty()){
                    Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = db.registerUser(role,
                        Name, email, phone, password,
                        vehicleModel, vehicleType
                );

                Toast.makeText(this,
                        success ? "User Registered Successfully" : "Registration Failed",
                        Toast.LENGTH_SHORT).show();

            } else if (rbOwner.isChecked()) {
                String businessRegNo = etBusinessRegNo.getText().toString().trim();
                String businessName = etBusinessName.getText().toString().trim();
                String businessType = etBusinessType.getText().toString().trim();
                String businessDescription = etBusinessDescription.getText().toString().trim();
                String businessAddress = etBusinessAddress.getText().toString().trim();
                String businessPhone = etBusinessPhone.getText().toString().trim();

                if(businessRegNo.isEmpty() || businessName.isEmpty() ||
                        businessType.isEmpty() || businessDescription.isEmpty() ||
                        businessAddress.isEmpty() || businessPhone.isEmpty()){
                    Toast.makeText(this, "Fill all required fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean success = db1.registerOwner(role,
                        Name, email, phone, password,
                        businessRegNo, businessName, businessType, businessDescription, businessAddress, businessPhone
                );

                Toast.makeText(this,
                        success ? "Owner Registered Successfully" : "Registration Failed",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Select a role", Toast.LENGTH_SHORT).show();
            }
        });
    }

}