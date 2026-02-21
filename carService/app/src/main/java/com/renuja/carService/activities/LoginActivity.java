package com.renuja.carService.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.renuja.carService.Database.DBHelperOwner;
import com.renuja.carService.Database.DBHelperUser;
import com.renuja.carService.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView tvRegister;
    String role, name, password;
    DBHelperUser db;
    DBHelperOwner db1;
    TextInputEditText etName, etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
        db = new DBHelperUser(this);
        db1 = new DBHelperOwner(this);

        Intent intent = getIntent();
        role = intent.getStringExtra("role");

        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(v -> {
            name = etName.getText().toString().trim();
            password = etPassword.getText().toString().trim();

            if (name.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if(role.equals("user")) {
                int id = db.checkLogin(name, password);
                if(id == -1){
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1 = new Intent(this, OwnerHomeActivity.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    finish();
                }
            } else if(role.equals("owner")){
                int id = db1.checkLogin(name, password);
                if(id == -1){
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent1 = new Intent(this, UserCategoriesActivity.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    finish();
                }
            } else{
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
            intent.putExtra("role", role);
            finish();
        });
	}

}