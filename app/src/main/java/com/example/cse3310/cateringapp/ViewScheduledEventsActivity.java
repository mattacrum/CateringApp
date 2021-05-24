/*package com.example.cse3310.cateringapp;

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

public class ViewScheduledEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scheduled_events);
    }
}

*/
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

public class ViewScheduledEventsActivity extends AppCompatActivity {
    Vector<EventModel> dbEvents = new Vector<EventModel>();
    UserModel caterer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scheduled_events);
        DBManager handler = new DBManager(this);
        List<String> scheduled_events = new ArrayList<String>();

        dbEvents = handler.retrieveEventStatus("scheduled");

        if(dbEvents != null) {
            String staffId = "";
            UserModel staff = new UserModel();
            for (EventModel event : dbEvents) {
              //  scheduled_events.add(event.getOccasion() + "\n" + event.getDate() + "\n" + event.getTime()/* + "\n" + event.getStaff()*/);
                staffId = String.valueOf(event.getStaff());
                staff = handler.retrieveUserById(staffId);

                if (staff != null) {
                    scheduled_events.add(event.getOccasion() + "\nDate: " + event.getDate() + "\nTime:" + event.getTime() +
                            "Staff: " + staff.getUserFName());
                }else {
                    scheduled_events.add(event.getOccasion() + "\nDate: " + event.getDate() + "\nTime:" + event.getTime() +
                            "\nNo Staff Assigned");
                }
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                scheduled_events
        );

        if (getIntent().hasExtra("caterer")) {
            caterer = (UserModel) getIntent().getSerializableExtra("caterer");
        }

        ListView scheduledEvents = (ListView) findViewById(R.id.scheduled_events);
        scheduledEvents.setAdapter(adapter);
        scheduledEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewScheduledEventsActivity.this, ViewScheduledEventDetails.class);
                intent.putExtra("eventPosition",position);
                intent.putExtra("event", dbEvents.get(position));
                intent.putExtra("caterer", caterer);
                startActivity(intent);
            }
        });


    }
}

