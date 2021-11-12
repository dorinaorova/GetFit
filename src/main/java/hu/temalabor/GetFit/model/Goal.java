package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.Date;

@Document("Goal")
public class Goal {
    @Id
    private int _id;
    private int Type;
    private int Amount;
    private int userId;
    private Date DateStart;
    private int CurrentAmount;
    private int Status;
    private int Distance;

    public Goal(int _id, int Amount, int userId, Date DateStart) {
        super();
        this._id = _id;
        Type = 0;
        this.Amount = Amount;
        this.userId = userId;
        this.DateStart = DateStart;
        this.CurrentAmount = 0;
        SetStatus();
    }


    public int GetType(){
        return Type;
    }
    public int Amount(){
        return Amount;
    }

    public int CurrentAmount(){
        return CurrentAmount;
    }

    public void SetStatus(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(DateStart);
        cal.add(Calendar.DATE, 7);
        if (Calendar.getInstance().after(cal) && CurrentAmount<Amount) Status =0;   //lejart az egy het, nem sikerult teljesiteni
        else if(CurrentAmount>=Amount) Status = 1;                                  //teljesitette
        else Status =2;                                                             //meg folyamatban van
    }

    //GETTERS
    public int getStatus(){
        return Status;
    }

    public int get_id() {
        return _id;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDateStart() {
        return DateStart;
    }

    public int getAmount() {
        return Amount;
    }

    public int getCurrentAmount() {
        return CurrentAmount;
    }

    //SETTERS
    public void setType(int type) {
        Type = type;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setCurrentAmount(int currentAmount) {
        CurrentAmount = currentAmount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateStart(Date dateStart) {
        DateStart = dateStart;
    }


    public int getDistance() {
        return Distance;
    }

    public void setDistance(int distance) {
        Distance = distance;
    }
}
