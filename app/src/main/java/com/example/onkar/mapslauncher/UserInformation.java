package com.example.onkar.mapslauncher;

public class UserInformation {

    private String UName;  //here U stands for User
    private String UEmail;
    private String ULati;
    private String ULongi;
    private String Uphone;
    private String EName; //Here E stands for Emerengency
    private String ENumber;

    public UserInformation(String IDUname,String IDEmail,String IDUphone ,String IDEname,String IDEnumber){
        this.UName = IDUname;
        this.UEmail = IDEmail;
        this.Uphone = IDUphone;
        this.EName = IDEname;
        this.ENumber = IDEnumber;

    }

    public String getUName() {
        return UName;
    }


    public String getUEmail() {
        return UEmail;
    }


    public String getUphone() {
        return Uphone;
    }


    public String getEName() {
        return  EName;
    }



    public String getENumber() {
        return  ENumber;
    }

    public String getULati() {
        return ULati;
    }

    public void setULati(String Lati) {
        this.ULati = Lati;
    }

    public String getULongi() {
        return ULongi;
    }
    public void setULongi(String Longi) {
        this.ULongi = Longi;
    }

}
