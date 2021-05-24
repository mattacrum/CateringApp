package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.cse3310.cateringapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText emailSection, passSection, uNameSection, fNameSection, lNameSection;
    Button registerBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailSection = findViewById(R.id.emailSection);
        passSection = findViewById(R.id.passSection);
        registerBtn = findViewById(R.id.registerBtn);
        uNameSection = findViewById(R.id.uNameSection);
        fNameSection = findViewById(R.id.fNameSection);
        lNameSection = findViewById(R.id.lNameSection);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.users_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  if (isInputValid(emailSection, passSection)){


                UserModel model = new UserModel();
                model.setUserUName(uNameSection.getText().toString());
                model.setUserFName(fNameSection.getText().toString());
                model.setUserLName(lNameSection.getText().toString());
                model.setUserRole(spinner.getSelectedItem().toString());
                model.setUserEmail(emailSection.getText().toString());
                model.setUserPassword(passSection.getText().toString());

                DBManager handler = new DBManager(RegisterActivity.this);
                handler.addNewUser(model);

                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }

        });
    }
}