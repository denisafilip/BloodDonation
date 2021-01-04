package database;

import user.BloodType;
import user.County;
import user.Donor;
import user.Scraper;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseOperations {

    private static final String INSERT_DONOR_IN_DATABASE = "INSERT INTO Donor (firstName, lastName, phoneNumber, dateOfBirth, age, email, password, gender, idBloodType, idCounty, cnp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_COUNTY_ID = "SELECT id FROM County WHERE name = ?";
    private static final String GET_BLOOD_TYPE_ID = "SELECT id FROM BloodType WHERE type = ? and rh = ?";
    private static final String CHECK_IF_DONOR_IN_DATABASE_SIGN_UP = "SELECT * from Donor WHERE email = ? and password = ? or CNP = ?";
    private static final String CHECK_IF_DONOR_IN_DATABASE_SIGN_IN = "SELECT * from Donor WHERE email = ? and password = ?";
    private static final String GET_DONOR_DATA = "SELECT firstName, lastName, phoneNumber, dateOfBirth, age, email, password, gender, idBloodType, idCounty FROM Donor WHERE email = ?";
    private static final String GET_BLOOD_TYPE_TYPE = "SELECT type FROM BloodType WHERE id = ?";
    private static final String GET_BLOOD_TYPE_RH = "SELECT rh FROM BloodType WHERE id = ?";
    private static final String GET_COUNTY = "SELECT * FROM County WHERE id = ?";
    private static final String INSERT_COUNTY_IN_DATABASE = "INSERT INTO County VALUES(?, ?)";


    /**
     * Used only once, when populating the database with the counties information.
     */
    public void insertCountiesInDatabase() throws SQLException {
        try (Connection conn = DatabaseConnectionFactory.getConnection()) {
            Scraper scraper = new Scraper();
            ArrayList<County> counties = scraper.webScrapingCounties();
            for (County c : counties) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(INSERT_COUNTY_IN_DATABASE)) {
                    preparedStatement.setString(1, c.getName());
                    preparedStatement.setInt(2, c.getNumberOfBloodBanks());
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    /**
     * Function to insert donor into Donor database, after successful sign up.
     * @param donor - the donor that we want to insert into the database
     */
    public void insertDonorInDatabase(Donor donor) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement insertUser = conn.prepareStatement(INSERT_DONOR_IN_DATABASE)) {

            int idCounty = getDonorCountyId(donor);
            int idBloodType = getDonorBloodTypeId(donor);

            insertUser.setString(1, donor.getFirstName());
            insertUser.setString(2, donor.getLastName());
            insertUser.setString(3, donor.getPhoneNumber());
            insertUser.setDate(4, Date.valueOf(donor.getDateOfBirth()));
            insertUser.setInt(5, donor.getAge());
            insertUser.setString(6, donor.getEmail());
            insertUser.setString(7, donor.getPassword());
            insertUser.setString(8, donor.getGender());
            insertUser.setInt(9, idBloodType);
            insertUser.setInt(10, idCounty);
            insertUser.setString(11, donor.getCNP());

            insertUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the id of the donor's county
     */
    public int getDonorCountyId(Donor donor) throws SQLException {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_COUNTY_ID)) {
            preparedStatement.setString(1, donor.getCounty().getName());
            int idCounty = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    idCounty = resultSet.getInt("id");
                }
            }
            return idCounty;
        }
    }

    /**
     * @return the id of the donor's blood type
     */
    public int getDonorBloodTypeId(Donor donor) throws SQLException {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_BLOOD_TYPE_ID)) {
            preparedStatement.setString(1, donor.getBloodType().getName());
            preparedStatement.setBoolean(2, donor.getBloodType().getRH());

            int idBloodType = 0;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idBloodType = resultSet.getInt("id");
                }
            }
            return idBloodType;
        }
    }

    public BloodType getDonorBloodType(int idBloodType) throws SQLException {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_BLOOD_TYPE_TYPE)) {
            preparedStatement.setInt(1, idBloodType);

            BloodType bloodType = null;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    bloodType = new BloodType(resultSet.getString("type"), resultSet.getBoolean("rh"));
                }
            }
            return bloodType;
        }
    }

    public County getDonorCounty(int idCounty) throws SQLException {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_COUNTY)) {
            preparedStatement.setInt(1, idCounty);

            County county = null;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    county = new County(resultSet.getString("name"), resultSet.getInt("numberOfBloodBanks"));
                }
            }
            return county;
        }
    }

    /** @return true if the donor is already found in the database. This is used only for sign up, by checking the combination email, password and
     * separately, the CNP.  */
    public Boolean isDonorInDatabaseSignUp(Donor donor) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(CHECK_IF_DONOR_IN_DATABASE_SIGN_UP)) {
            preparedStatement.setString(1, donor.getEmail());
            preparedStatement.setString(2, donor.getPassword());
            preparedStatement.setString(3, donor.getCNP());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /** @return true if the donor is found in the database. This is used only for sign in, by checking the username and password
     * introduced by the donor.*/
    public Boolean isDonorInDatabaseSignIn(Donor donor) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(CHECK_IF_DONOR_IN_DATABASE_SIGN_IN)) {
            preparedStatement.setString(1, donor.getEmail());
            preparedStatement.setString(2, donor.getPassword());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get donor's date by email, after sign in. Then the static variable currentDonor is updated to this.
     */
    public Donor getDonorData(String email) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(GET_DONOR_DATA)) {
            preparedStatement.setString(1, email);
            Donor donor = null;
            try (ResultSet r = preparedStatement.executeQuery()) {
                while (r.next()) {
                    donor = new Donor(r.getString("firstName"), r.getString("lastName"),
                            r.getString("phoneNumber"), r.getDate("dateOfBirth").toLocalDate(),
                            r.getString("email"), r.getString("password"), getDonorBloodType(r.getInt("idBloodType")), getDonorCounty(r.getInt("idCounty")),
                            r.getString("CNP"));
                }
            }
            return donor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
