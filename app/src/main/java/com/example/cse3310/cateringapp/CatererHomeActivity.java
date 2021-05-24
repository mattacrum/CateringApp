package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CatererHomeActivity extends AppCompatActivity {
    public Button view_requested_events;
    public Button view_scheduled_events;
    public Button assignStaff;
    Button logout;
    UserModel caterer;

    public void view_requested_events_nav()
    {
        if (getIntent().hasExtra("caterer")) {
            caterer = (UserModel)getIntent().getSerializableExtra("caterer");
        }
        view_requested_events =  (Button)findViewById(R.id.view_requested_events);
        view_requested_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatererHomeActivity.this, ViewRequestedEventsActivity.class);
                intent.putExtra("caterer", caterer);
                startActivity(intent);
            }
        });
    }

    public void view_scheduled_events_nav()
    {
        view_scheduled_events =  (Button)findViewById(R.id.view_scheduled_events);
        view_scheduled_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatererHomeActivity.this, ViewScheduledEventsActivity.class);
                startActivity(intent);
            }
        });
    }

    public void assignStaffNav() {
        assignStaff = findViewById(R.id.assign_staff);

        assignStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clicker = new Intent(CatererHomeActivity.this, AssignStaffActivity.class);
                startActivity(clicker);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_home);
        view_requested_events_nav();
        view_scheduled_events_nav();
        assignStaffNav();

        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(CatererHomeActivity.this, MainActivity.class);
                startActivity(intentReg);
            }
        });
    }
}
