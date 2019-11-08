package Models.Entities;

public class FullTimeLecturer extends Lecturer {

    private String salary;

    public FullTimeLecturer() {}

    public FullTimeLecturer(String fullName, String address, String contactNumber, String email,
                            String startDate, String salary) {

        super(fullName, address, contactNumber, email, "Full Time", startDate);

        this.salary = salary;

    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
