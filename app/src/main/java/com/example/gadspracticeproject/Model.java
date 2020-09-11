package com.example.gadspracticeproject;

import com.google.gson.annotations.SerializedName;

public class Model {
    @SerializedName("name")
    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName(value = "hours", alternate = "score")
    private int hours;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getHours() {
        return hours;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", hours=" + hours +
                '}';
    }
}
