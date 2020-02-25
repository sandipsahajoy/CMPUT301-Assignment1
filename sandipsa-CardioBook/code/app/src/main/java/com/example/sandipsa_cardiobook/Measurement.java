package com.example.sandipsa_cardiobook;

import java.io.Serializable;

/**
 * The Measurement class, defines the structure and functionality of a measurement,
 * it only contains, a constructor, getters and setters.
 **/

public class Measurement implements Serializable {
    private String date;
    private String time;
    private String systolic;
    private String diastolic;
    private String heartrate;
    private String comment;


    // Constructor
    public Measurement(String date, String time, String systolic, String diastolic, String heartrate, String comment)
    {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartrate = heartrate;
        this.comment = comment;
    }
    // getters
    public String getSystolic() {
        return systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public String getDate() { return date; }

    public String getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }


    // setters
    public void setSystolic(String systolic) {
        this.systolic =  systolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic =  diastolic;
    }

    public void setHeartrate(String heartrate) {
        this.heartrate = heartrate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
