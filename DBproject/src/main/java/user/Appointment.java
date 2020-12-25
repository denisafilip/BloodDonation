package user;

import java.time.format.DateTimeFormatter;

public class Appointment {
    private DateTimeFormatter appointmentDate;
    private Donor donor;
    private BloodBank bloodBank;

    public Appointment(DateTimeFormatter appointmentDate, Donor donor, BloodBank bloodBank) {
        this.appointmentDate = appointmentDate;
        this.donor = donor;
        this.bloodBank = bloodBank;
    }

    public DateTimeFormatter getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(DateTimeFormatter appointmentDate) {
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
