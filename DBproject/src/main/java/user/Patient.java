package user;

public class Patient {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private String gender;
    private String bloodType;
    private Boolean rh;
    private String county;
    private String CNP;
    private String hospital;
    private Boolean recieved;

    public Patient(String firstName, String lastName,String gender, String phoneNumber, int age, String bloodType, Boolean rh, String county, String CNP, String hospital, Boolean recieved) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.bloodType = bloodType;
        this.rh = rh;
        this.county = county;
        this.CNP = CNP;
        this.hospital = hospital;
        this.recieved = recieved;
        this.gender = gender;
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

    public Boolean getRh() {
        return rh;
    }

    public void setRh(Boolean rh) {
        this.rh = rh;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCnp() {
        return CNP;
    }

    public void setCnp(String CNP) {
        this.CNP = CNP;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Boolean getRecieved() {
        return recieved;
    }

    public void setRecieved(Boolean recieved) {
        this.recieved = recieved;
    }

    public boolean verifyFirstName() {
        return (this.firstName.matches("[a-zA-Z- ]+"));
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return true if user's last name is formed only of letters, spaces and hyphens
     */
    public boolean verifyLastName() {
        return (this.lastName.matches("[a-zA-Z- ]+"));
    }

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
     * @return true if the phone number contains only digits
     */
    public Boolean verifyPhoneNumberr(){
        if(this.phoneNumber.matches("[0-9]+") && this.phoneNumber.length() == 10){
            return true;
        }
        else{
            System.err.println("The phone number must contain only digits and have the length equals to 10.");
            return false;
        }
    }

    /**
     * @returns true if the blood type is valid
     */

    public Boolean verifyBloodType(){
        if(this.bloodType.equals("01") || this.bloodType.equals("A2") || this.bloodType.equals("B3") || this.bloodType.equals("AB4")){
            return true;
        }
        else {
            System.err.println("The blood type is incorrect.");
            return false;
        }
    }

}
