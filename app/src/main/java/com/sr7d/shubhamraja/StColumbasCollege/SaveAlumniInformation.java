package com.sr7d.shubhamraja.StColumbasCollege;

/**
 * Created by Shubham Raja on 1/21/2017.
 */

public class SaveAlumniInformation {

    public String Address,CName,Occupation,Designation,WorkExp;
    public SaveAlumniInformation(){

    }

    public SaveAlumniInformation(String address, String CName, String occupation, String designation, String workExp) {
        Address = address;
        this.CName = CName;
        Occupation = occupation;
        Designation = designation;
        WorkExp = workExp;
    }
}
