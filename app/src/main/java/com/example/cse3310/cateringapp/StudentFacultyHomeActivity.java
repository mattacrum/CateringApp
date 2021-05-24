package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StudentFacultyHomeActivity extends AppCompatActivity {
    Button request_event, view_reserved_events, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_faculty_home);
        DBManager handler = new DBManager(StudentFacultyHomeActivity.this);
        request_event =findViewById(R.id.request_event);
        view_reserved_events = findViewById(R.id.view_reserved_events);
        logout = findViewById(R.id.logout);

        request_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(StudentFacultyHomeActivity.this, RequestEventActivity.class);
                startActivity(intentReg);
            }
        });

        view_reserved_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(StudentFacultyHomeActivity.this, ViewReservedEventActivity.class);
                startActivity(intentReg);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(StudentFacultyHomeActivity.this, MainActivity.class);
                startActivity(intentReg);
            }
        });
    }
}
