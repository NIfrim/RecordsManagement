package Models.Entities;

import java.time.LocalDate;

public class Lecturer extends Staff {

    protected String startDate;
    protected String contractType;
    protected String status;

    public Lecturer() {

        this(null, null, null, null,
            null, null);
    }

    public Lecturer(String fullName, String address, String contactNumber, String email,
                    String contractType, String startDate) {

        super(fullName, address, contactNumber, email);

        this.contractType = contractType;
        this.startDate = startDate;

    }

    public void setStatus(LocalDate startDate) {

    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setContract(String contractType) {
        this.contractType = contractType;
    }


    public String getStatus() {
        LocalDate startDate = LocalDate.parse(this.startDate);
        if (startDate.isAfter(LocalDate.now())) {
            return "Pending";
        }
        else {
            return "Active";
        }
    }

    public String getStartDate() {
        return startDate;
    }

    public String getContract() {
        return contractType;
    }

}
