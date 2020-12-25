package user;

import java.time.LocalDate;

public class Patient extends User {

    private Hospital hospital;
    private Boolean received;

    public Patient(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String gender, String bloodType, boolean RH, County county, String CNP, Hospital hospital) {
        super(firstName, lastName, phoneNumber, dateOfBirth, gender, bloodType, RH, county, CNP);
        this.hospital = hospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean recieved) {
        this.received = recieved;
    }
}
