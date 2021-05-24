
package com.example.cse3310.cateringapp;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Vector;
        import android.content.Intent;
        import android.widget.AdapterView.OnItemClickListener;

public class ViewRequestedEventsActivity extends AppCompatActivity {
    Vector<EventModel> dbEvents = new Vector<EventModel>();
    UserModel caterer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requested_events);
        DBManager handler = new DBManager(this);
        List<String> requested_events = new ArrayList<String>();
        dbEvents = handler.retrieveEventStatus("pending");
        if(dbEvents != null) {
            for (EventModel event : dbEvents) {
                    requested_events.add(event.getOccasion() + "\n" + event.getDate() + "\n" + event.getTime());

              }

            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    requested_events
            );

            if (getIntent().hasExtra("caterer")) {
                caterer = (UserModel) getIntent().getSerializableExtra("caterer");
            }

            ListView requestedEvents = (ListView) findViewById(R.id.requested_events);
            requestedEvents.setAdapter(adapter);
            requestedEvents.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewRequestedEventsActivity.this, ViewRequestedEventDetailsActivity.class);
                    intent.putExtra("eventPosition",position);
                    intent.putExtra("event", dbEvents.get(position));
                    intent.putExtra("caterer", caterer);
                    startActivity(intent);
                }
            });


        }
    }

