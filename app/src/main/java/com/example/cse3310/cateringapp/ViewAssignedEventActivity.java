package com.example.cse3310.cateringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ViewAssignedEventActivity extends AppCompatActivity {
    Vector<EventModel> dbEvents = new Vector<EventModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_event);
        DBManager handler = new DBManager(this);
        List<String> assigned_events = new ArrayList<String>();
        dbEvents = handler.retrieveEvents();
        if (dbEvents != null) {
            for (EventModel event : dbEvents) {
                assigned_events.add(event.getOccasion() + "\n" + event.getDate() + "\n" + event.getTime());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    assigned_events
            );
            ListView assignedEvents = (ListView) findViewById(R.id.assigned_events);
            assignedEvents.setAdapter(adapter);
          /*  assignedEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewAssignedEventActivity.this, ViewRequestedEventDetailsActivity.class);
                    intent.putExtra("eventPosition", position);
                    intent.putExtra("event", dbEvents.get(position));
                    startActivity(intent);
                }
            });
            */
        }
    }
}
