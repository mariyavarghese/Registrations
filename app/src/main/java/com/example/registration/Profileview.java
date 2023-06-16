package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.registration.databinding.ActivityMainBinding;

import java.util.ArrayList;


public class Profileview extends AppCompatActivity {

ActivityMainBinding binding;
DataBaseHelper databaseHelper;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DataBaseHelper(this);
        name = getIntent().getStringExtra("name");
        getUserDetails();
    }

    private void getUserDetails() {
        databaseHelper = new DataBaseHelper(this);
        ArrayList<UserModel> ls = databaseHelper.displayProfile(name);
        UserModel userModel = ls.get(0);
        binding.name.setText(userModel.getName());
        binding.gender.setText(userModel.getGender());
        binding.status.setText(userModel.getStatus());
        binding.guard.setText(userModel.getGuard());
        binding.blood.setText(userModel.getBlood());
        binding.age.setText(userModel.getAge());
        binding.address.setText(userModel.getAddress());
        binding.category.setText(userModel.getCategory());
        binding.date.setText(userModel.getDate());

    }
}