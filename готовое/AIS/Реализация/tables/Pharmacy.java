package tables;

import tables.Medicine;

import java.util.ArrayList;
import java.util.List;

public class Pharmacy {
    String id, address, image, openTime, closeTime;
    List<Staff> staff;
    List<Medicine> medicines;

    public Pharmacy(String id, String address, String image, String openTime, String closeTime, ArrayList<Staff> staff, ArrayList<Medicine> medicines) {
        this.id = id;
        this.address = address;
        this.image = image;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.staff = staff;
        this.medicines = medicines;
    }

    public Pharmacy(String id, String address, String image, String openTime, String closeTime) {
        this.id = id;
        this.address = address;
        this.image = image;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    public String getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
    public List<Staff> getStaff() {
        return staff;
    }
    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }
    public List<Medicine> getMedicines() {
        return medicines;
    }
    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }
}