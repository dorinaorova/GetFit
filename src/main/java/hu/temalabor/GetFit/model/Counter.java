package hu.temalabor.GetFit.model;

public class Counter {
    private String _id;
    private  int counter;

    public Counter(String _id, int counter){
        this._id=_id;
        this.counter=counter;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increaseCounter() {
        counter++;
    }
}
