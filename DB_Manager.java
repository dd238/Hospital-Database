package com.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


//retrofit to match hospital objects
//connects to database and performs query/updates

public class DB_Manager {

    Scanner keyboard = new Scanner(System.in);
    MySQL_Connection mysqlConnection = new MySQL_Connection();
    Hospital_Objects hospitalRecord = new Hospital_Objects();
    Hospital_Manager hosManager = new Hospital_Manager();
    Doctor_Manager docManager = new Doctor_Manager();
    Patient_Manager patManager = new Patient_Manager();
    Connection connection;
    PreparedStatement pS;
    ResultSet rset;
    String hospitalPassword;
    String doctorPassword;
    String patientPassword;
    boolean isLoggedIn = true;

    int hospitalID;
    String hospitalName;
    String hospitalAddress;
    String hospitalNum;
    String isHospitalDeleted;

    int serviceID;
    String serviceName;
    String medsPrescribed;

    int doctorID;
    String doctorName;
    String doctorNum;
    String doctorSpecialty;
    String isDoctorDeleted;

    int patientID;
    String patientName;
    String patientNum;
    String patientAddress;
    String patientSymptoms;
    String isPatientDeleted;

    int paymentID;
    String paymentBy;
    String paymentDate;

    int option;

    public void verifyAdmin(int hospitalID, String hospitalPassword) throws SQLException {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pS = connection.prepareStatement("SELECT * FROM Hospital_Login WHERE hospitalID = " + hospitalID +
                    " AND hospitalPassword = '" + hospitalPassword + "'");
            rset = pS.executeQuery();

            while (rset != null && rset.next()) {   // Move the cursor to the next row, return false if no more row\
                this.hospitalID = rset.getInt("hospitalID");
                this.hospitalPassword = rset.getString("hospitalPassword");
            }

        } catch (SQLException e) {
            System.out.println("\nUsername or Password Invalid");
        }
        if(this.hospitalID == 0 || this.hospitalPassword.equals(null))
        {
            System.out.println("\nUsername or Password Invalid");
            return;
        }

        do{
                System.out.println("\nEnter Number for Command:\n" +
                        "1.) Display\n" +
                        "2.) Create\n" +
                        "3.) Update\n" +
                        "4.) Delete\n" +
                        "5.) Search\n" +
                        "6.) Logout\n");

                option = keyboard.nextInt();
                keyboard.nextLine();
                switch (option) {
                    case 1:
                        hosManager.display(hospitalID);
                        break;
                    case 2:
                        hosManager.create(hospitalID);
                        break;
                    case 3:
                        hosManager.update();
                        break;
                    case 4:
                        hosManager.delete();
                        break;
                    case 5:
                        hosManager.search();
                        break;
                    case 6:
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }
        }while(isLoggedIn == true);
    }

    public void verifyDoctor(int doctorID, String doctorPassword) throws SQLException {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pS = connection.prepareStatement("SELECT * FROM Doctor_Login WHERE doctorID = " + doctorID +
                    " AND doctorPassword = '" + doctorPassword + "'");
            rset = pS.executeQuery();

            while (rset != null && rset.next()) {   // Move the cursor to the next row, return false if no more row\
                this.doctorID = rset.getInt("doctorID");
                this.doctorPassword = rset.getString("doctorPassword");
            }

        } catch (SQLException e) {
            System.out.println("\nUsername or Password Invalid");
        }
        if(this.doctorID == 0 || this.doctorPassword.equals(null))
        {
            System.out.println("\nUsername or Password Invalid");
            return;
        }
        do{
                System.out.println("\nEnter Number for Command:\n" +
                        "1.) Display\n" +
                        "2.) Create\n" +
                        "3.) Update\n" +
                        "4.) Delete\n" +
                        "5.) Search\n" +
                        "6.) Logout\n");

                option = keyboard.nextInt();
                keyboard.nextLine();
                switch (option) {
                    case 1:
                        docManager.display(doctorID);
                        break;
                    case 2:
                        docManager.create(doctorID);
                        break;
                    case 3:
                        docManager.update(doctorID);
                        break;
                    case 4:
                        docManager.delete();
                        break;
                    case 5:
                        docManager.search();
                        break;
                    case 6:
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }

        }while(isLoggedIn == true);

    }
    public void verifyPatient(int patientID, String patientPassword) throws SQLException {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            pS = connection.prepareStatement("SELECT * FROM Patient_Login WHERE patientID = " + patientID +
                    "  AND patientPassword = '" + patientPassword + "'");
            rset = pS.executeQuery();

            while (rset != null && rset.next()) {   // Move the cursor to the next row, return false if no more row\
                this.patientID = rset.getInt("patientID");
                this.patientPassword = rset.getString("patientPassword");
            }
        } catch (SQLException e) {
            System.out.println("\nUsername or Password Invalid");
        }
        if(this.patientID == 0 || this.patientPassword.equals(null))
        {
            System.out.println("\nUsername or Password Invalid");
            return;
        }

        do {
                System.out.println("\nEnter Number for Command:\n" +
                        "1.) Display\n" +
                        "2.) Update\n" +
                        "3.) Delete\n" +
                        "4.) Search\n" +
                        "5.) Logout\n");

                option = keyboard.nextInt();
                keyboard.nextLine();
                switch (option) {
                    case 1:
                        patManager.display(patientID);
                        break;
                    case 2:
                        patManager.update(patientID);
                        break;
                    case 3:
                        patManager.delete(patientID);
                        break;
                    case 4:
                        patManager.search(patientID);
                        break;
                    case 5:
                        isLoggedIn = false;
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }

        }while(isLoggedIn == true);

    }
}
