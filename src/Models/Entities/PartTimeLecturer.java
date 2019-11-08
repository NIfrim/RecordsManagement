package Models.Entities;

public class PartTimeLecturer extends Lecturer {

    private String hourlyRate;

    public PartTimeLecturer() {}

    public PartTimeLecturer(String fullName, String address, String contactNumber, String email,
                            String startDate, String hourlyRate) {

        super(fullName, address, contactNumber, email, "Part Time", startDate);

        this.hourlyRate = hourlyRate;

    }

    public void setHourlyRate(String hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getHourlyRate() {
        return hourlyRate;
    }
}
