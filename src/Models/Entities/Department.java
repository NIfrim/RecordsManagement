package Models.Entities;

import Core.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Department extends Model implements Serializable {

    private ArrayList<Lecturer> lecturers;

    private String departmentId;
    private String departmentName;
    private String departmentType;
    private String webAddress;
    private String Username;
    private String Password;

    public Department() {

        this(null, null, null, null, null,
                null);

    }

    public Department(String departmentId, String departmentName, String departmentType, String webAddress,
                      String Username, String Password)
    {
        this.departmentName = departmentName;
        this.departmentType = departmentType;
        this.webAddress = webAddress;
        this.Username = Username;
        this.Password = Password;
    }


    public void setId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentType(String departmentType) {
        this.departmentType = departmentType;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public void setPassword(String password) {
        this.Password = password;
    }


    public String getId() {
        return departmentId;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentType() {
        return departmentType;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }
}
