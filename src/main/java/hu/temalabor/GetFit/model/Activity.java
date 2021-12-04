package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Activity")
public class Activity {
    @Id
    private int _id;                 //azonosito
    private int sportId;            //sport azonosito
    private int userId;             //felhasznalo azonosito
    private double Time;             //sporttal tolott ido (perc)
    private long Date;    //sportolas kezdete
    private double Kcal;             //elegetett kaloriak
    private double Distance;


    public Activity(int _id, int sportId, int userId, double Time, long Date) {
        super();
        this._id = _id;
        this.sportId = sportId;
        this.userId = userId;
        this.Time = Time;
        this.Date=Date;
    }


    public void calculateKcal(double kcal, double weight){
        Kcal= kcal*weight*Time;
    }
    public void setKcal(double kcal){Kcal=kcal;}

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public void setDate(long date) {
        Date = date;
    }

    public void setSportId(int sportId) {
        this.sportId = sportId;
    }

    public void setTime(double time) {
        Time = time;
    }

    public int get_id() {
        return _id;
    }

    public int getSportId() {
        return sportId;
    }

    public int getUserId() {
        return userId;
    }

    public double getTime() {
        return Time;
    }

    public long getDate() {
        return Date;
    }

    public double getKcal() {
        return Kcal;
    }

    public double getDistance() {
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
    }
}
