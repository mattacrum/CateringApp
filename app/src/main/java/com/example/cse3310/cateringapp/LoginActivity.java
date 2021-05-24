package com.example.cse3310.cateringapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{
    EditText emailSection, passwordSection;
    Button loginBtn;
    static int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailSection = findViewById(R.id.email);
        passwordSection = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        DBManager db = new DBManager(LoginActivity.this);
        db.addTestData();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isInputValid(emailSection, passwordSection)) {

                    String role;
                    DBManager handler = new DBManager(LoginActivity.this);
                    UserModel user = handler.retrieveUser(emailSection.getText().toString(), passwordSection.getText().toString());
                    user_id = user.getId();
                    if (user != null) {
                        Intent intent;
                        role = user.getUserRole();
                        if (role.equals("UTA user")) {
                            intent = new Intent(LoginActivity.this, StudentFacultyHomeActivity.class);
                        } else if (role.equals("Caterer")) {
                            intent = new Intent(LoginActivity.this, CatererHomeActivity.class);
                            intent.putExtra("caterer", user);
                        } else if (role.equals("Staff")) {
                            intent = new Intent(LoginActivity.this, StaffHomeActivity.class);
                        } else {
                            Toast.makeText(LoginActivity.this, "INVALID USER TYPE, PLEASE REGISTER NEW ACCOUNT", Toast.LENGTH_LONG).show();
                            intent = new Intent(LoginActivity.this, RegisterActivity.class);
                        }
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "WRONG EMAIL ID OR PASSWORD", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean isInputValid(EditText emailSection, EditText passSection) {

        boolean status = true;

        if (emailSection.getText().toString().length() < 2) {
            emailSection.setError("Please enter valid email ID");
            status = false;
        }

        if (passSection.getText().toString().length() < 2) {
            passSection.setError("Please enter valid password");
            status = false;
        }

        return status;
    }
}