package com.example.cse3310.cateringapp;
import java.io.Serializable;

//enum Formality {formal,informal}
//enum Meal {breakfast,lunch,supper}
//enum Drink {alcohol,non_alcohol}
//enum Food {chinese,italian,mexican,greek,japanese,french,american,indian,pizza}

public class EventModel implements Serializable{
    private int eid;
    private String time;
    private String date;
    private String occasion;
    private String formality;
    private String mealType;
    private String foodVenue;
    private String drinkType;
    private String partySize;
    private int caterer; // The id of the caterer in the users databases
    private int staff;
    private int user;
    private String status;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getFormality() {
        return formality;
    }

    public void setFormality(String formality) {
        this.formality = formality;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFoodVenue() {
        return foodVenue;
    }

    public void setFoodVenue(String foodVenue) {
        this.foodVenue = foodVenue;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public int getCaterer() { return caterer; }

    public void setCaterer(int caterer) { this.caterer = caterer; }

    public void setStaff(int staff) { this.staff = staff; }

    public int getStaff() { return staff; }

    public int getUser() { return user; }

    public void setUser(int user) { this.user = user; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
