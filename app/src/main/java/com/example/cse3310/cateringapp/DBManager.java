package com.example.cse3310.cateringapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DBManager extends SQLiteOpenHelper{

    //Create DB
    private static final int Db_VERSION = 1;

    private static final String DB_NAME = "catering_app_db";
    // Table names
    private static final String USER_TABLE = "users";
    private static final String EVENT_TABLE = "events";
    // Users table columns
    private static final String KEY_UNAME  = "user_uname";
    private static final String KEY_FNAME  = "user_fname";
    private static final String KEY_LNAME  = "user_lname";
    private static final String KEY_ID = "user_id";
    private static final String KEY_ROLE   = "user_role";
    private static final String KEY_EMAIL  = "user_email";
    private static final String KEY_PASS   = "user_pass";
    // Event table columns
    private static final String KEY_EID = "event_id";
    private static final String KEY_DATE = "event_date";
    private static final String KEY_TIME = "event_time";
    private static final String KEY_OCC = "occassion";
    private static final String KEY_FORM = "formality";
    private static final String KEY_MEAL = "meal_type";
    private static final String KEY_DRINK = "drink_type";
    private static final String KEY_FOOD = "food_venue";
    private static final String KEY_PARTY = "size";
    private static final String KEY_STAFF = "staff";
    private static final String KEY_CATERER = "caterer";
    private static final String KEY_USER = "uta_user";
    private static final String KEY_STATUS = "status";

    public DBManager(Context context) {
        super(context, DB_NAME, null, Db_VERSION);
    }

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + USER_TABLE + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_UNAME + " TEXT,"
            + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_ROLE + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PASS + " TEXT )";

    private static final String CREATE_TABLE_EVENT = "CREATE TABLE " + EVENT_TABLE + "(" + KEY_EID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_DATE + " TEXT,"
            + KEY_TIME + " TEXT," + KEY_OCC + " TEXT," + KEY_FORM + " TEXT," + KEY_MEAL + " TEXT," + KEY_PARTY + " TEXT," + KEY_DRINK + " TEXT," + KEY_FOOD + " TEXT,"
            + KEY_STATUS + " TEXT," + KEY_CATERER + " INTEGER," + KEY_USER + " INTEGER," + KEY_STAFF + " INTEGER )";


    @Override
    public void onCreate(SQLiteDatabase sqLitedb) {
        sqLitedb.execSQL(CREATE_TABLE_USER);
        sqLitedb.execSQL(CREATE_TABLE_EVENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLitedb, int i, int i1) {
        sqLitedb.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLitedb.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        onCreate(sqLitedb);
    }

    public void addNewUser(UserModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UNAME, user.getUserUName());
        values.put(KEY_FNAME, user.getUserFName());
        values.put(KEY_LNAME, user.getUserLName());
        values.put(KEY_ROLE, user.getUserRole());
        values.put(KEY_EMAIL, user.getUserEmail());
        values.put(KEY_PASS, user.getUserPassword());
        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public void addTestData() {
        UserModel user1 = new UserModel();
        UserModel staff1 = new UserModel();
        UserModel staff2 = new UserModel();
        UserModel caterer1 = new UserModel();
        EventModel event1 = new EventModel();
        EventModel event2 = new EventModel();

        user1.setUserUName("user1");
        user1.setUserFName("Captain");
        user1.setUserLName("America");
        user1.setUserRole("UTA user");
        user1.setUserEmail("user1");
        user1.setUserPassword("user");

        staff1.setUserUName("staff1");
        staff1.setUserFName("Sam");
        staff1.setUserLName("Smith");
        staff1.setUserRole("Staff");
        staff1.setUserEmail("staff1");
        staff1.setUserPassword("staff");

        staff2.setUserUName("staff2");
        staff2.setUserFName("Sean");
        staff2.setUserLName("O'Connor");
        staff2.setUserRole("Staff");
        staff2.setUserEmail("staff2");
        staff2.setUserPassword("staff");

        caterer1.setUserUName("caterer1");
        caterer1.setUserFName("John");
        caterer1.setUserLName("Adams");
        caterer1.setUserRole("Caterer");
        caterer1.setUserEmail("caterer1");
        caterer1.setUserPassword("caterer");

        event1.setOccasion("PARRRTAYYY");
        event1.setMealType("Chinese");
        event1.setDrinkType("Alcoholic");
        event1.setFormality("Formal");
        event1.setFoodVenue("Arlington");
        event1.setPartySize("1-10");
        event1.setDate("30-3-2018");
        event1.setTime("4:08am");
        event1.setStatus("pending");
        event1.setUser(1);

        event2.setOccasion("Chillin");
        event2.setMealType("Chinese");
        event2.setDrinkType("Alcoholic");
        event2.setFormality("Formal");
        event2.setFoodVenue("Arlington");
        event2.setPartySize("1-10");
        event2.setDate("30-3-2018");
        event2.setTime("4:08am");
        event2.setStatus("pending");
        event2.setUser(1);

        addNewEvent(event1);
        addNewEvent(event2);
        addNewUser(user1);
        addNewUser(staff1);
        addNewUser(staff2);
        addNewUser(caterer1);
    }

    public void addUserToEvent(String userId, String eventId, String userRole) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        if (userRole.equals("Staff")) {
            cv.put(KEY_STAFF, userId);
        }
        else if (userRole.equals("UTA user")) {
            cv.put(KEY_USER, userId);
        }
        else if (userRole.equals("Caterer")) {
            cv.put(KEY_CATERER, userId);
        } else {
            return;
        }
        db.update(EVENT_TABLE, cv, KEY_EID+"=?", new String[]{eventId});
    }

    public void addNewEvent (EventModel event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_TIME, event.getTime());
        values.put(KEY_OCC, event.getOccasion());
        values.put(KEY_FORM, event.getFormality());
        values.put(KEY_MEAL, event.getMealType());
        values.put(KEY_DRINK, event.getDrinkType());
        values.put(KEY_FOOD, event.getFoodVenue());
        values.put(KEY_PARTY, event.getPartySize());
        values.put(KEY_USER, LoginActivity.user_id);
        values.put(KEY_STATUS, event.getStatus());
        db.insert(EVENT_TABLE, null, values);
        db.close();
    }
    public UserModel retrieveUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + USER_TABLE + " WHERE " + KEY_EMAIL + " = \""
                + email + "\" AND " + KEY_PASS + " = \"" + password + "\";";
        Cursor cursor = db.rawQuery(query, null);

        UserModel model = new UserModel();

        if (cursor.moveToFirst()) {
            model.setUserUName(cursor.getString(cursor.getColumnIndex(KEY_UNAME)));
            model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
            model.setUserEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            model.setUserPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//            temp_id = model.getId();
        } else {
            model = null;
        }
        cursor.close();
        return model;
    }

    public UserModel retrieveUserById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + USER_TABLE + " WHERE " + KEY_ID + "= " + id + ";";
        Cursor cursor = db.rawQuery(query, null);

        UserModel model = new UserModel();

        if (cursor.moveToFirst()) {
            model.setUserUName(cursor.getString(cursor.getColumnIndex(KEY_UNAME)));
            model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
            model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
            model.setUserRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
            model.setUserEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            model.setUserPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
//            temp_id = model.getId();
        } else {
            model = null;
        }
        cursor.close();
        return model;
    }

    public void addStatusToEvent(String eventId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_STATUS, status);
        db.update(EVENT_TABLE, cv, KEY_EID+"=?", new String[]{eventId});
    }

    public EventModel retrieveEvent(String eventname, String datetime) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * from " + EVENT_TABLE + " WHERE " + KEY_EID + "= \"" + eventname +  "\"" + datetime + "\";";
        Cursor cursor = db.rawQuery(query, null);

        EventModel model = new EventModel();

        if (cursor.moveToFirst()) {
            model.setEid(cursor.getInt(cursor.getColumnIndex(KEY_EID)));
            //model.setEventName(cursor.getString(cursor.getColumnIndex(KEY_ENAME)));
            model.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            model.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
            model.setOccasion(cursor.getString(cursor.getColumnIndex(KEY_OCC)));
            model.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORM)));
            model.setMealType(cursor.getString(cursor.getColumnIndex(KEY_MEAL)));
            model.setDrinkType(cursor.getString(cursor.getColumnIndex(KEY_DRINK)));
            model.setFoodVenue(cursor.getString(cursor.getColumnIndex(KEY_FOOD)));
            model.setPartySize(cursor.getString(cursor.getColumnIndex(KEY_PARTY)));
        } else {
            model = null;
        }
        cursor.close();
        return model;
    }

    public Vector<EventModel> retrieveID(int checker) {
        SQLiteDatabase db = this.getWritableDatabase();
        String can = " \"Cancelled\" ";
        String query = "SELECT  * FROM " + EVENT_TABLE + " WHERE " + KEY_STATUS + " != " + can + "AND " + KEY_USER + "= \"" + checker + "\";";
        Cursor cursor = db.rawQuery(query, null);

        Vector<EventModel> events = new Vector<EventModel>();
        if (cursor.moveToFirst()) {
            do {
                EventModel model = new EventModel();
                model.setEid(cursor.getInt(cursor.getColumnIndex(KEY_EID)));
                model.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                model.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                model.setOccasion(cursor.getString(cursor.getColumnIndex(KEY_OCC)));
                model.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORM)));
                model.setMealType(cursor.getString(cursor.getColumnIndex(KEY_MEAL)));
                model.setDrinkType(cursor.getString(cursor.getColumnIndex(KEY_DRINK)));
                model.setFoodVenue(cursor.getString(cursor.getColumnIndex(KEY_FOOD)));
                model.setPartySize(cursor.getString(cursor.getColumnIndex(KEY_PARTY)));
                model.setStaff(cursor.getInt(cursor.getColumnIndex(KEY_STAFF)));
                model.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));

                events.add(model);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return events;
    }

    public Vector<EventModel> retrieveEvents() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + EVENT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        Vector<EventModel> events = new Vector<EventModel>();

        if (cursor.moveToFirst()) {
            do {
                EventModel model = new EventModel();
                model.setEid(cursor.getInt(cursor.getColumnIndex(KEY_EID)));
                //model.setEventName(cursor.getString(cursor.getColumnIndex(KEY_ENAME)));
                model.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                model.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                model.setOccasion(cursor.getString(cursor.getColumnIndex(KEY_OCC)));
                model.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORM)));
                model.setMealType(cursor.getString(cursor.getColumnIndex(KEY_MEAL)));
                model.setDrinkType(cursor.getString(cursor.getColumnIndex(KEY_DRINK)));
                model.setFoodVenue(cursor.getString(cursor.getColumnIndex(KEY_FOOD)));
                model.setPartySize(cursor.getString(cursor.getColumnIndex(KEY_PARTY)));
                model.setStaff(cursor.getInt(cursor.getColumnIndex(KEY_STAFF)));
                model.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
                model.setCaterer(cursor.getInt(cursor.getColumnIndex(KEY_CATERER)));
                model.setUser(cursor.getInt(cursor.getColumnIndex(KEY_USER)));

                events.add(model);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return events;
    }

    public Vector<EventModel> retrieveEventStatus(String eventstatus) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  * FROM " + EVENT_TABLE+ " WHERE " + KEY_STATUS + "= \"" + eventstatus + "\";";
        Cursor cursor = db.rawQuery(query, null);

        Vector<EventModel> events = new Vector<EventModel>();

        if (cursor.moveToFirst()) {
            do {
                EventModel model = new EventModel();
                model.setEid(cursor.getInt(cursor.getColumnIndex(KEY_EID)));
                //model.setEventName(cursor.getString(cursor.getColumnIndex(KEY_ENAME)));
                model.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                model.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                model.setOccasion(cursor.getString(cursor.getColumnIndex(KEY_OCC)));
                model.setFormality(cursor.getString(cursor.getColumnIndex(KEY_FORM)));
                model.setMealType(cursor.getString(cursor.getColumnIndex(KEY_MEAL)));
                model.setDrinkType(cursor.getString(cursor.getColumnIndex(KEY_DRINK)));
                model.setFoodVenue(cursor.getString(cursor.getColumnIndex(KEY_FOOD)));
                model.setPartySize(cursor.getString(cursor.getColumnIndex(KEY_PARTY)));
                model.setStaff(cursor.getInt(cursor.getColumnIndex(KEY_STAFF)));
                model.setStatus(cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
                model.setCaterer(cursor.getInt(cursor.getColumnIndex(KEY_CATERER)));
                model.setUser(cursor.getInt(cursor.getColumnIndex(KEY_USER)));

                events.add(model);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return events;
    }



    public Vector<UserModel> retrieveUserType(String userRole) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Gets only users of the specified role
        String query = "SELECT * from " + USER_TABLE + " WHERE " + KEY_ROLE + "= \"" + userRole + "\";";
        Cursor cursor = db.rawQuery(query, null);

        // Created vector to store entire user model
        Vector<UserModel> userType = new Vector<UserModel>();

        if (cursor.moveToFirst()) {
            do {
                // Only storing critical information for display. Password, email, will be null
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
                model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
                model.setUserRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                model.setUserUName(cursor.getString(cursor.getColumnIndex(KEY_UNAME)));

                userType.add(model);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return userType;
    }

    /*public Vector<UserModel> retrieveUserName(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Gets only users of the specified role
        String query = "SELECT * from " + USER_TABLE + " WHERE " + KEY_ID + "= \"" + userId + "\";";
        Cursor cursor = db.rawQuery(query, null);

        // Created vector to store entire user model
        Vector<UserModel> userType = new Vector<UserModel>();

        if (cursor.moveToFirst()) {
            do {
                // Only storing critical information for display. Password, email, will be null
                UserModel model = new UserModel();
                model.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                model.setUserFName(cursor.getString(cursor.getColumnIndex(KEY_FNAME)));
               model.setUserLName(cursor.getString(cursor.getColumnIndex(KEY_LNAME)));
                model.setUserRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                model.setUserUName(cursor.getString(cursor.getColumnIndex(KEY_UNAME)));

                userType.add(model);
            } while (cursor.moveToNext());
        } else {
            return null;
        }
        cursor.close();
        return userType;
    }*/

    /**
     * Getting all events
     * returns list of events
     * */
    public List<String> getAllEvents(){
        List<String> events = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + EVENT_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                events.add("Occasion:" + cursor.getString(3) + "\nDate: " + cursor.getString(1) + "\nTime: " + cursor.getString(2));

                events.add(cursor.getString(3));


            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning events
        return events;
    }
}
