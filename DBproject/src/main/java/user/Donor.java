package user;

import java.time.LocalDate;

public class Donor extends User {

    private String email;
    private int weight;

    public Donor(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String gender, String bloodType, boolean RH, String county, String CNP, String email, int weight) {
        super(firstName, lastName, phoneNumber, dateOfBirth, gender, bloodType, RH, county, CNP);
        this.email = email;
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
