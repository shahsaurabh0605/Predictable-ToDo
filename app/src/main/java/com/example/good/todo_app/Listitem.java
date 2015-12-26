package com.example.good.todo_app;

/**
 * Created by good on 25/12/15.
 */
public class Listitem {
    private String name,details,date,time,main,sub;

    public Listitem(String sub,String details,String date,String time, String main) {
        this.main = main;
        this.sub = sub;
        this.name = main + " : " + sub;
        this.details = details;
        this.date=date;
        this.time=time;
    }

    public String getName(){return this.name;}
    public String getDetails(){return this.details;}
    public String getDate(){return this.date;}
    public String getTime(){return this.time;}
    public String getMain(){return this.main;}
    public String getSub(){return this.sub;}

    public void setName(String a, String b){this.name = a + " : " + b;}
    public void setDes(String a){this.details = a;}
    public void setDate(String a){this.date = a;}
    public void setTime(String a){this.time = a;}
    public void setMain(String a){this.main = a;}
    public void setSub(String a){this.sub = a;}
}