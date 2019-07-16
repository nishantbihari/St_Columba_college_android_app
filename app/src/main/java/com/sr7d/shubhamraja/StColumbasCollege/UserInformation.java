package com.sr7d.shubhamraja.StColumbasCollege;

/**
 * Created by Shubham Raja on 1/5/2017.
 */

public class UserInformation {

    public String name;
    public String Address,fName,mName,fOccupation,hsName,marks,percentage;
    public UserInformation(){

    }

    public UserInformation(String name, String address, String fName, String mName, String fOccupation, String hsName, String marks, String percentage) {
        this.name = name;
        Address = address;
        this.fName = fName;
        this.mName = mName;
        this.fOccupation = fOccupation;
        this.hsName = hsName;
        this.marks = marks;
        this.percentage = percentage;
    }
}
