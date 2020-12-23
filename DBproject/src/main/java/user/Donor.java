package user;

import java.time.LocalDate;
import java.time.Period;

public class Donor {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private int age;
    private String gender;
    private String bloodType;
    private int weight;
    private String county;
    private String CNP;

    public Donor(String firstName, String lastName, String email, String phoneNumber, LocalDate dateOfBirth, String gender, String bloodType, int weight, String county, String CNP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = computeUserAge();
        this.gender = gender;
        this.bloodType = bloodType;
        this.weight = weight;
        this.county = county;
        this.CNP = CNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
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

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    /**
     *
     * @return true if donor's CNP is valid and meets all of the requirements
     */
    public boolean verifyCNP() {
        String constant = "279146358279";
        if (!this.CNP.matches("[0-9]+")) {
            System.err.println("You must enter only numbers for the CNP");
            return false;
        }
        if (this.CNP.length() != 13) {
            System.err.println("The CNP must have 13 digits.");
            return false;
        }
        int sum = 0;
        for (int i = 0; i < constant.length(); i++) {
            sum = sum + this.CNP.charAt(i) * constant.charAt(i);
        }
        int rest = sum % 11;
        int controlDigit = Character.getNumericValue(this.CNP.charAt(12));
        if ((rest < 10 && rest == controlDigit) || (rest == 10 && controlDigit == 1)) {
            if ((this.gender.equals("F") && (this.CNP.charAt(0) == '6' || this.CNP.charAt(0) == '2')) ||
                    (this.gender.equals("M") && (this.CNP.charAt(0) == '5' || this.CNP.charAt(0) == '1'))) {
                return true;
            } else {
                System.err.println("The first digit of your CNP does not match your gender.");
                return false;
            }
        } else {
            System.err.println("The CNP you entered is not valid.");
            return false;
        }
    }

    /**
     *
     * @return user's age, computed as a difference of the current date and the user's date of birth
     */
    public int computeUserAge() {
        LocalDate now = LocalDate.now();
        Period difference = Period.between(this.dateOfBirth, now);
        return difference.getYears();
    }

    /**
     *
     * @return true if user is over 18 and under 110 years old (eligible to donate blood)
     */
    public boolean isEligibleToDonate() {
        return (this.age >= 18 && this.age < 65);
    }


    /**
     *
     * @return true if user's first name is formed only of letters, spaces and hyphens
     */
    public boolean verifyFirstName() {
        return (this.firstName.matches("[a-zA-Z- ]+"));
    }

    /**
     *
     * @return true if user's last name is formed only of letters, spaces and hyphens
     */
    public boolean verifyLastName() {
        return (this.lastName.matches("[a-zA-Z- ]+"));
    }
}
