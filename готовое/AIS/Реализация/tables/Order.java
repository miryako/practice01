package tables;

import java.util.ArrayList;

public class Order {
    String id, date, pharmacy, userId;
    int count;
    ArrayList<String> medicines;

    public Order(String id, String date, String pharmacy, int count, ArrayList<String> medicines, String userId) {
        this.id = id;
        this.date = date;
        this.pharmacy = pharmacy;
        this.count = count;
        this.medicines = medicines;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getPharmacy() {
        return pharmacy;
    }
    public void setPharmacy(String pharmacy) {
        this.pharmacy = pharmacy;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public ArrayList<String> getMedicines() {
        return medicines;
    }
    public void setMedicines(ArrayList<String> medicines) {
        this.medicines = medicines;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}