package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("Activity")
public class Activity {
    @Id
    private int _id;                 //azonosito
    private int SportId;            //sport azonosito
    private int userId;             //felhasznalo azonosito
    private double Time;             //sporttal tolott ido (perc)
    private Date Date;    //sportolas kezdete
    private double Kcal;             //elegetett kaloriak
    private Sport sport;
    private User user;

    public Activity(int _id, int SportId, int userId, double Time, Date Date) {
        super();
        this._id = _id;
        this.SportId = SportId;
        this.userId = userId;
        this.Time = Time;
        this.Date = Date;

    }


    public void setKcal (){
        Kcal= sport.getKcal()* user.getWeight()*Time;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setKcal(double kcal) {
        Kcal = kcal;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public void setSportId(int sportId) {
        SportId = sportId;
    }

    public void setTime(double time) {
        Time = time;
    }

    public int get_id() {
        return _id;
    }

    public int getSportId() {
        return SportId;
    }

    public int getUserId() {
        return userId;
    }

    public double getTime() {
        return Time;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public double getKcal() {
        return Kcal;
    }
}
