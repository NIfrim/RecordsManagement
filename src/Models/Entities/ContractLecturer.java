package Models.Entities;


public class ContractLecturer extends Lecturer {

    private String salary;
    private String endDate;

    public ContractLecturer() {}

    public ContractLecturer(String fullName, String address, String contactNumber, String email,
                            String startDate, String salary, String endDate) {

        super(fullName, address, contactNumber, email, "Contract", startDate);

        this.salary = salary;
        this.endDate = endDate;

    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public String getSalary() {
        return salary;
    }

    public String getEndDate() {
        return endDate;
    }
}
