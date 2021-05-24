package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AssignStaffActivity extends AppCompatActivity{

    Spinner staffSpinner, eventSpinner;
    Button assignStaff;
    Vector<EventModel> dbEvents = new Vector<EventModel>();
    Vector<UserModel> dbStaff = new Vector<UserModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_staff);

        staffSpinner = findViewById(R.id.staff);
        eventSpinner = findViewById(R.id.events);
        assignStaff = findViewById(R.id.add_staff_to_event);


        loadSpinnerData();

        assignStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBManager handler = new DBManager(AssignStaffActivity.this);
                String selectedStaffName = staffSpinner.getSelectedItem().toString();
                String selectedEventName = eventSpinner.getSelectedItem().toString();
                String staffId = "", eventId = "";
                if (dbStaff != null && dbEvents != null) {
                    for (UserModel staff : dbStaff) {
                        if (staff.getUserFName().equals(selectedStaffName)) {
                            staffId = String.valueOf(staff.getId());
                            break;
                        }
                    }
                    for (EventModel event : dbEvents) {
                        if (event.getOccasion().equals(selectedEventName)) {
                            eventId = String.valueOf(event.getEid());
                        }
                    }
                }

                handler.addUserToEvent(staffId, eventId, "Staff");
                String toastMsg =  selectedStaffName + " added to event";
                Toast.makeText(AssignStaffActivity.this, toastMsg, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AssignStaffActivity.this, CatererHomeActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        // database handler
        DBManager db = new DBManager(getApplicationContext());

        dbEvents = db.retrieveEvents();
        dbStaff = db.retrieveUserType("Staff");
        List<String> events = new ArrayList<String>();
        List<String> staff = new ArrayList<String>();

        if (dbStaff != null && dbEvents != null) {
            // Spinner Drop down elements
            for (EventModel event : dbEvents) {
                events.add(event.getOccasion());
            }

            for (UserModel user : dbStaff) {
                staff.add(user.getUserFName());
                System.out.println(user.getUserFName());
            }
        }

        // Creating adapter for spinners
        ArrayAdapter<String> staffAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, staff);
        ArrayAdapter<String> eventAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, events);

        // Drop down layout style - list view with radio button
        staffAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinners
        staffSpinner.setAdapter(staffAdapter);
        eventSpinner.setAdapter(eventAdapter);
    }

}
