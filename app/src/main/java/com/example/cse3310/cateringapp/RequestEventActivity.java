package com.example.cse3310.cateringapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class RequestEventActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    EditText occasion;
    TextView date_result, time_result;
    Spinner partySize, mealType, drinkType, formality, venue;
    int day, month, year, hour, minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;
    Button request, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_event);
        // For date and picker
        time_result = findViewById(R.id.time_result);
        date_result = findViewById(R.id.date_result);
        time = findViewById(R.id.time);
        // Storing values of user input to store into database
        occasion = findViewById(R.id.occasion);
        request = findViewById(R.id.request);
        partySize = findViewById(R.id.party_size);
        mealType = findViewById(R.id.meal_type);
        drinkType = findViewById(R.id.drink_type);
        formality = findViewById(R.id.formality);
        venue = findViewById(R.id.venue);

        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RequestEventActivity.this, RequestEventActivity.this,
                        year, month, day);
                datePickerDialog.show();
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventModel model = new EventModel();
                model.setOccasion(occasion.getText().toString());
                model.setMealType(mealType.getSelectedItem().toString());
                model.setDrinkType(drinkType.getSelectedItem().toString());
                model.setFormality(formality.getSelectedItem().toString());
                model.setFoodVenue(venue.getSelectedItem().toString());
                model.setPartySize(partySize.getSelectedItem().toString());
                model.setDate(date_result.getText().toString());
                model.setTime(time_result.getText().toString());
                model.setStatus("pending");
                DBManager handler = new DBManager(RequestEventActivity.this);
//                String eventId = "";
//                eventId = String.valueOf(model.getEid());
                handler.addNewEvent(model);
//                handler.addStatusToEvent(eventId, "pending");
                Toast.makeText(RequestEventActivity.this, "Event Request Submitted!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(RequestEventActivity.this, StudentFacultyHomeActivity.class);
                startActivity(intent);
            }
        });
    }
    // Sets date f
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal = year;
        monthFinal = month;
        dayFinal = dayOfMonth;

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        date_result.setText(dayFinal+"-"+monthFinal+"-"+yearFinal);

        TimePickerDialog timePickerDialog = new TimePickerDialog(RequestEventActivity.this, RequestEventActivity.this, hour, minute, false);
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hourFinal = hourOfDay;
        minuteFinal = minute;

        time_result.setText(hourFinal+":"+minuteFinal+"\n");
    }
}