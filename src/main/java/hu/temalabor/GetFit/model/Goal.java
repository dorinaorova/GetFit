package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Document("Goal")
public class Goal {
    @Id
    private int _id;
    private int Amount;
    private int userId;
    private long DateStart;
    private int CurrentAmount ;
    private int Status = 0;

    public Goal(int _id, int Amount, int userId, long DateStart) {
        super();
        this._id = _id;
        this.Amount = Amount;
        this.userId = userId;
        this.DateStart = DateStart;
        this.CurrentAmount = 0;
        SetStatus();
    }

    public void SetStatus(){
        Calendar cal = Calendar.getInstance();
        Timestamp ts = new Timestamp(DateStart);
        cal.setTime(new Date(ts.getTime()));
        int days= cal.get(Calendar.DAY_OF_WEEK);
        if (days==1) days+=7;
        days-=cal.getFirstDayOfWeek();
        cal.add(Calendar.DATE,-days); //first day of week

        if (Calendar.getInstance().after(cal) && CurrentAmount<Amount) Status =0;   //lejart az egy het, nem sikerult teljesiteni
        else if(CurrentAmount>=Amount) {
            Status = 1;                                                             //teljesitette
        }
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

    public long getDateStart() {
        return DateStart;
    }

    public int getAmount() {
        return Amount;
    }

    public int getCurrentAmount() {
        return CurrentAmount;
    }

    //SETTERS

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public void setCurrentAmount(int currentAmount) {
        CurrentAmount += currentAmount;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateStart(long dateStart) {
        DateStart = dateStart;
    }

}
