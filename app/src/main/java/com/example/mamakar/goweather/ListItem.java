package com.example.mamakar.goweather;

/**
 * Created by Mamakar on 30/05/17.
 */

public class ListItem {


    private String date;
    private String city;
    private  String description;
    private  String type;

    private Double temp;


    public ListItem(String date , String city , String description ,String Type, Double temp){

        this.date=date;
        this.city=city;
        this.description=description;
        this.type=type;
        this.temp=temp;
    }

    public String getCity(){

        return city;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
}
