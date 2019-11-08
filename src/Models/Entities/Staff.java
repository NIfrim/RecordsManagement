package Models.Entities;

import Core.Model;

import java.io.Serializable;

public class Staff extends Model implements Serializable {

    protected String department;
    protected String id;
    protected String fullName;
    protected String city;
    protected String postcode;
    protected String address;
    protected String contactNumber;
    protected String email;

    public Staff() {
        this(null, null, null, null);
    }

    public Staff(String fullName, String address, String email, String contactNumber)
    {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.contactNumber = contactNumber;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }



    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }
}
