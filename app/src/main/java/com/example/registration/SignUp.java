package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.registration.databinding.ActivitySignUpBinding;

import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    ActivitySignUpBinding binding;
    DataBaseHelper databaseHelper;
    int year,month,day;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{6,}" +                // at least 6 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DataBaseHelper(this);




     binding.date.setOnClickListener(this);


        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.name.getText().toString();
                RadioButton checked = findViewById(binding.radioGroup.getCheckedRadioButtonId());
                String gender = checked.getText().toString();
                RadioButton checkstatus = findViewById(binding.status.getCheckedRadioButtonId());
                String status = checkstatus.getText().toString();
                String guard = binding.guard.getText().toString();
                String blood = binding.blood.getText().toString();
                String age = binding.age.getText().toString();
                String address = binding.address.getText().toString();
                String phone = binding.phone.getText().toString();
                String category = binding.category.getText().toString();
                String date = binding.date.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirmPassword = binding.signupConfirm.getText().toString();


                if(name.equals("")){
                    Toast.makeText(SignUp.this, "name is mandatory", Toast.LENGTH_SHORT).show();
                }else if(checked.equals("")){
                    Toast.makeText(SignUp.this, "Please select the gender", Toast.LENGTH_SHORT).show();
                }else if(status.equals("")){
                    Toast.makeText(SignUp.this, "Marrital Status is mandatory", Toast.LENGTH_SHORT).show();
                }else if(guard.equals("")){
                    Toast.makeText(SignUp.this, "Father/Husband name is mandatory", Toast.LENGTH_SHORT).show();
                }else if(blood.equals("")){
                    Toast.makeText(SignUp.this, "Blood Group is mandatory", Toast.LENGTH_SHORT).show();
                }else if(age.equals("")){
                    Toast.makeText(SignUp.this, "age is mandatory", Toast.LENGTH_SHORT).show();
                }else if(address.equals("")){
                    Toast.makeText(SignUp.this, "Address is mandatory", Toast.LENGTH_SHORT).show();
                }else if(category.equals("")){
                    Toast.makeText(SignUp.this, "category is mandatory", Toast.LENGTH_SHORT).show();
                }else if(date.equals("")){
                    Toast.makeText(SignUp.this, "date of registration is mandatory", Toast.LENGTH_SHORT).show();
                }else if(password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(SignUp.this, "password is mandatory", Toast.LENGTH_SHORT).show();
                }else if(!password.equals(confirmPassword)){
                    Toast.makeText(SignUp.this, "password missmatch", Toast.LENGTH_SHORT).show();
                } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                    Toast.makeText(SignUp.this, "Password is too weak", Toast.LENGTH_SHORT).show();
                }else{


                    if(password.equals(confirmPassword)) {
                        Boolean checkUserName = databaseHelper.checkName(name);
                        if(checkUserName == false) {
                            Boolean insert = databaseHelper.insertData(name,gender,status,guard,blood,age,address,category,date,password);
                            if(insert == true){
                                Toast.makeText(SignUp.this, "Signup Successfully!", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUp.this, "Signup Failed!", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(SignUp.this, "User already exists! Please login", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUp.this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });



    }

    @Override
    public void onClick(View view) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        binding.date.setText(i+"/"+(i1+1)+"/"+i2);
                    }
                },year,month,day);
        dpd.show();

    }
}