package user;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private Timestamp appointmentDate;
    private Donor donor;
    private BloodBank bloodBank;

    public Appointment(Timestamp appointmentDate, Donor donor, BloodBank bloodBank) {
        this.appointmentDate = appointmentDate;
        this.donor = donor;
        this.bloodBank = bloodBank;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public BloodBank getBloodBank() {
        return bloodBank;
    }

    public void setBloodBank(BloodBank bloodBank) {
        this.bloodBank = bloodBank;
    }
}
