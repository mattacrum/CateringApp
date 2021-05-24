package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class ViewScheduledEventDetails extends AppCompatActivity {
    TextView date_result,time_result,occasion, partySize,mealType,drinkType,formality,venue;
    int position;
    private EventModel event = new EventModel();
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scheduled_event_details);

        // For date and picker
        time_result = findViewById(R.id.time_result);
        date_result = findViewById(R.id.date_result);
        // Storing values of user input to store into database
        occasion = findViewById(R.id.occasion);
        cancel = findViewById(R.id.Cancel);
        partySize = findViewById(R.id.party_size);
        mealType = findViewById(R.id.meal_type);
        drinkType = findViewById(R.id.drink_type);
        formality = findViewById(R.id.formality);
        venue = findViewById(R.id.venue);

        if (getIntent().hasExtra("eventPosition")) {
            position = getIntent().getIntExtra("eventPosition", 0);
            event = (EventModel) getIntent().getSerializableExtra("event");
        }
        occasion.setText(event.getOccasion());
        partySize.setText(event.getPartySize());
        date_result.setText(event.getDate());
        time_result.setText(event.getTime());
        mealType.setText(event.getMealType());
        drinkType.setText(event.getDrinkType());
        formality.setText(event.getFormality());
        venue.setText(event.getFoodVenue());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBManager db = new DBManager(getApplicationContext());
                Vector<EventModel> dbEvents = db.retrieveEvents();
                Vector<UserModel> dbCaterer = db.retrieveUserType("Caterer");
                String eventId = "";
                if (event != null) {
                    eventId = String.valueOf(event.getEid());

                    db.addStatusToEvent(eventId,"Cancelled");
                    String toastMsg =  "Event Cancelled";
                    Toast.makeText(ViewScheduledEventDetails.this, toastMsg, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ViewScheduledEventDetails.this, "Error", Toast.LENGTH_LONG).show();
                }

                Intent intent = new Intent(ViewScheduledEventDetails.this, ViewScheduledEventsActivity.class);
                startActivity(intent);
            }
        });
    }
}
