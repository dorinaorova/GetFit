package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Sport")
public class Sport {

    @Id
    private int _id;
    private  String Type;
    private double Kcal;            //sport altal elegetheto kaloriak (1kg, 1 percre)

    public Sport(int _id, String Type, double Kcal){
        super();
        this._id =_id;
        this.Type =Type;
        this.Kcal=Kcal;

    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setKcal(double kcal) {
        Kcal = kcal;
    }

    public void setType(String type) {
        Type = type;
    }

    public  String getType(){
        return Type;
    }

   public double getKcal() {
        return Kcal;
    }

    public int get_id(){
        return _id;
    }
}
