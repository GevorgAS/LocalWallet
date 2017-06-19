package ipc.gev.localwallet.db.entity;


public class Trade {
    private long id;
    private String markups;
    private String location;
    private long price;
    private String date;
    private int status;
    public static final int INCOME = 1;
    public static final int EXPENSE = 0;



    public Trade(){

    }



    public Trade(String markups, String location, long price, String date, int status) {
        this.markups = markups;
        this.location = location;
        this.price = price;
        this.date = date;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getMarkups() {
        return markups;
    }

    public void setMarkups(String markups) {
        this.markups = markups;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Trade{" + id+
                " markups='" + markups + '\'' +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
