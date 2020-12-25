package user;

import java.time.LocalDate;

public class Donor extends User {

    private String email;
    private String password;
    private int weight;

    public Donor(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth, String gender, String bloodType, boolean RH, County county, String CNP, String email, String password) {
        super(firstName, lastName, phoneNumber, dateOfBirth, gender, bloodType, RH, county, CNP);
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
