package com.example.cse3310.cateringapp;
// Update

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewReservedEventActivity extends AppCompatActivity {
    Vector<EventModel> dbEvents = new Vector<EventModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reserved_event);
        DBManager handler = new DBManager(this);

        List<String> reserved_events = new ArrayList<String>();
        dbEvents = handler.retrieveID(LoginActivity.user_id);
        if(dbEvents != null){
            String catererId = "";
            UserModel caterer = new UserModel();
            for (EventModel event : dbEvents) {
                catererId = String.valueOf(event.getCaterer());
                caterer = handler.retrieveUserById(catererId);

                if (caterer != null) {
                    reserved_events.add(event.getOccasion() + "\nDate: " + event.getDate() + "\nTime:" + event.getTime() +
                            "\nCaterer: " + caterer.getUserFName() + "\nStatus: " + event.getStatus());
                } else {
                    reserved_events.add(event.getOccasion() + "\nDate: " + event.getDate() + "\nTime:" + event.getTime() +
                            "\nCaterer: " + "\nStatus: " + event.getStatus());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reserved_events);
            ListView view_reserved_events = (ListView) findViewById(R.id.view_reserved_events_list);
            view_reserved_events.setAdapter(adapter);
            view_reserved_events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewReservedEventActivity.this, ViewReservedEventDetails.class);
                    intent.putExtra("eventPosition",position);
                    intent.putExtra("event", dbEvents.get(position));
                    startActivity(intent);
                }
            });
        }

    }
}
