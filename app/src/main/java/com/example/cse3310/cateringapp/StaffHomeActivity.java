package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StaffHomeActivity extends AppCompatActivity {
    public Button view_assigned_events;
    Button logout;

    public void view_assigned_events_nav()
    {
        view_assigned_events =  (Button)findViewById(R.id.view_assigned_events);
        view_assigned_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clicker = new Intent(StaffHomeActivity.this, ViewAssignedEventActivity.class);
                startActivity(clicker);
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home);
        view_assigned_events_nav();

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(StaffHomeActivity.this, MainActivity.class);
                startActivity(intentReg);
            }
        });
    }
}
