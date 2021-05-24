package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.System.*;
import java.util.*;

public class ViewRequestedEventDetailsActivity extends AppCompatActivity {

    List<String> partySizeArray = Arrays.asList("1-10","11-20","21-40","41-70","71-100");
    TextView date_result, time_result, occasion, partySize, mealType, drinkType, formality, venue;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    Button confirm, reject;
    UserModel caterer;

    private int position;
    private EventModel event = new EventModel();

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_requested_event_details);

        // For date and picker
        time_result = findViewById(R.id.time_result);
        date_result = findViewById(R.id.date_result);
        // Storing values of user input to store into database
        occasion = findViewById(R.id.occasion);
        partySize = findViewById(R.id.party_size);
        mealType = findViewById(R.id.meal_type);
        drinkType = findViewById(R.id.drink_type);
        formality = findViewById(R.id.formality);
        venue = findViewById(R.id.venue);
        confirm = findViewById(R.id.confirmBtn);
        reject = findViewById(R.id.rejectBtn);


        if (getIntent().hasExtra("eventPosition")) {
            position = getIntent().getIntExtra("eventPosition",0);
            event = (EventModel)getIntent().getSerializableExtra("event");
        }

        if (getIntent().hasExtra("caterer")) {
            caterer = (UserModel) getIntent().getSerializableExtra("caterer");
        }

        occasion.setText(event.getOccasion());
        partySize.setText(event.getPartySize());
        date_result.setText(event.getDate());
        time_result.setText(event.getTime());
        mealType.setText(event.getMealType());
        drinkType.setText(event.getDrinkType());
        formality.setText(event.getFormality());
        venue.setText(event.getFoodVenue());

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager db = new DBManager(getApplicationContext());
                Vector<EventModel> dbEvents = db.retrieveEvents();
                Vector<UserModel> dbCaterer = db.retrieveUserType("Caterer");
                String catererId = "", eventId = "";
                if (caterer != null && event != null) {
                    catererId = String.valueOf(caterer.getId());
                    eventId = String.valueOf(event.getEid());

                    db.addUserToEvent(catererId, eventId, "Caterer");
                    db.addStatusToEvent(eventId,"scheduled");
                    String toastMsg =  "Status set to scheduled";
                    Toast.makeText(ViewRequestedEventDetailsActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewRequestedEventDetailsActivity.this, "Caterer or event null", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(ViewRequestedEventDetailsActivity.this, CatererHomeActivity.class);
                startActivity(intent);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager db = new DBManager(getApplicationContext());
                Vector<EventModel> dbEvents = db.retrieveEvents();
                Vector<UserModel> dbCaterer = db.retrieveUserType("Caterer");
                String catererId = "", eventId = "";
                if (caterer != null && event != null) {
                    catererId = String.valueOf(caterer.getId());
                    eventId = String.valueOf(event.getEid());

                    db.addUserToEvent(catererId, eventId, "Caterer");
                    db.addStatusToEvent(eventId,"cancelled");
                    String toastMsg =  "Event cancelled";
                    Toast.makeText(ViewRequestedEventDetailsActivity.this, toastMsg, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewRequestedEventDetailsActivity.this, "Caterer or event null", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(ViewRequestedEventDetailsActivity.this, CatererHomeActivity.class);
                startActivity(intent);
            }
        });

    }

}
