package hu.temalabor.GetFit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User {
    @Id
    private int _id;                 //azonosito
    private String Username;        //felhasznalonev
    private String Name;            //nev
    private String Email;           //emailcim
    private String Password;        //jelszo
    private int Points;             //pontok
    private int Level;              //szint
    private long Birthdate;         //szuletesi ido
    private double Height;             //magasssag
    private double Weight;             //suly
    private boolean Gender;         //nem true=No; false=Ferfi

    public User(int _id, String Username, String Name, String Email, String Password, long Birthdate) {
        super();
        this._id = _id;
        this.Username = Username;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Birthdate=Birthdate;
        Points = 0;
        Level = 0;
    }

    public double getWeight() {
        return Weight;
    }
    public double getHeight() {
        return Height;
    }

    public int get_id() {
        return _id;
    }

    public String getUsername() {
        return Username;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public int getPoints() {
        return Points;
    }

    public int getLevel() {
        return Level;
    }

    public long getBirthdate() {
        return Birthdate;
    }

    public boolean getGender() {
        return Gender;
    }
    public void setPoints(int i){Points=i;}

    public void addPoints(int points) {
        Points += points;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBirthdate(long birthdate) {
        Birthdate = birthdate;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }

    public void setLevel() {
        Level = Points/10;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setWeight(double weight) {
        Weight = weight;
    }


}
