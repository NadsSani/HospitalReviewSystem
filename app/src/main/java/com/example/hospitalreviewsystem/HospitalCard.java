package com.example.hospitalreviewsystem;

public class HospitalCard {

    private String Title;
    private String Place;
    private String Rating;
    private String Details;

    public HospitalCard(String title, String place, String rating, String details) {
        Title = title;
        Place = place;
        Rating = rating;
        Details = details;
    }

    public String getTitle() {
        return Title;
    }

    public String getPlace() {
        return Place;
    }

    public String getRating() {
        return Rating;
    }

    public String getDetails() {
        return Details;
    }
}
