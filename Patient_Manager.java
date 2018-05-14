package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient_Manager {

    Scanner keyboard = new Scanner(System.in);
    MySQL_Connection mysqlConnection = new MySQL_Connection();
    Hospital_Objects hospitalRecord = new Hospital_Objects();
    Connection connection;
    PreparedStatement pS;
    ResultSet rset;
    int display;
    String displayType;
    boolean isDefault = true;
    String sqlDisplay;
    int create;
    int update;
    int updateType;
    int delete;
    int search;
    int searchType;

    int hospitalID;
    String hospitalName;
    String hospitalAddress;
    String hospitalNum;

    int serviceID;
    String serviceName;
    String medsPrescribed;

    int doctorID;
    String doctorName;
    String doctorNum;
    String doctorSpecialty;

    int patientID;
    String patientName;
    String patientNum;
    String patientAddress;
    String patientSymptoms;

    int paymentID;
    String paymentBy;
    String paymentDate;

    String isDeleted;


    public void display(int patientID, BufferedWriter bw) throws SQLException, IOException {

        System.out.println("\nDisplaying Payments: ");

        sqlDisplay = "SELECT * FROM Payments WHERE isPaymentDeleted IS NULL AND patientID = " + patientID;

        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pS = connection.prepareStatement(sqlDisplay);
        rset = pS.executeQuery();

        bw.write(pS.toString());

        System.out.println("\nYour payment records:");

        //update with getter and setter methods instead of variables

        while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
            paymentID = rset.getInt("paymentID");
            String paymentBy = rset.getString("paymentBy");
            String paymentDate = rset.getString("paymentDate");
            int serviceID = rset.getInt("serviceID");
            this.patientID = rset.getInt("patientID");
            String isPaymentDeleted = rset.getString("isPaymentDeleted");
            System.out.println(paymentID + ", " + paymentBy + ", " + paymentDate + ", " + serviceID + ", " +
                    patientID);
        }
    }

    public void update(int patientID, BufferedWriter bw) throws SQLException, IOException {

        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        do{
            System.out.println("\nWould you like to update the Patient's:\n" +
                    "1.) Number\n" +
                    "2.) Address\n" +
                    "3.) Password\n");
            updateType = keyboard.nextInt();
            keyboard.nextLine();
            if (updateType < 3 || updateType > 0) {
                isDefault = false;
            } else {
                isDefault = true;
            }
        }while (isDefault == true);

        switch (updateType) {
            //need to update to ensure generated keys are obtained
            case 1:
                System.out.println("\nEnter the Patient Number: ");
                hospitalRecord.setPatientNum(keyboard.nextLine());
                try {
                    pS = connection.prepareStatement("UPDATE Patients SET patientNum = '" + hospitalRecord.getPatientNum() +
                            "' WHERE patientID = " + patientID);
                } catch (Exception e) {
                    return;
                }
                break;
            case 2:
                System.out.println("\nEnter the Patient Address: ");
                hospitalRecord.setPatientAddress(keyboard.nextLine());
                try {
                    pS = connection.prepareStatement("UPDATE Patients SET patientAddress = '" + hospitalRecord.getPatientAddress() +
                            "' WHERE patientID = " + patientID);
                } catch (Exception e) {
                    return;
                }
                break;
            case 3:
                System.out.println("\nEnter the Patient Password: ");
                hospitalRecord.setPatientPassword(keyboard.nextLine());
                pS = connection.prepareStatement("UPDATE Patient_Login SET patientPassword = " + hospitalRecord.getPatientPassword() +
                        " WHERE patientID = " + hospitalRecord.getPatientID());
                break;
            default:
                System.out.println("\nInput Error.");
                break;
        }
        bw.write(pS.toString());
        connection.setAutoCommit(false); // No commit per statement
        try{
            pS.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void delete(int patientID, BufferedWriter bw) throws SQLException, IOException {

        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

            //need to update to ensure generated keys are obtained

        System.out.println("\nAre you sure you wish to delete your record with Health Services Solutions (y/n)");
        String answer = keyboard.nextLine();
        if(answer.toUpperCase().equals("Y"))
        {
            pS = connection.prepareStatement("UPDATE Patients SET isPatientDeleted = 1" +
                    " WHERE patientID = " + patientID);
        }
        bw.write(pS.toString());
        connection.setAutoCommit(false);
        try{
            pS.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        }
    }

    public void search(int patientID, BufferedWriter bw) throws SQLException, IOException {
        do{
            System.out.println("\nEnter Number to Search:\n" +
                    "1.) Hospitals\n" +
                    "2.) Doctors\n");
            search = keyboard.nextInt();
            if (search < 3 || search > 0) {
                isDefault = false;
            } else {
                isDefault = true;
            }
        }while (isDefault == true);

        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (search) {
            case 1:
                do{
                    System.out.println("\nEnter Number to Display by:\n" +
                            "1.) Hospital Name\n" +
                            "2.) Hospital Address\n" +
                            "3.) Hospital Number\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 4|| searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Hospital Name to search for: ");
                        hospitalRecord.setHospitalName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalName LIKE '%" + hospitalRecord.getHospitalName() + "%' AND isHospitalDeleted IS NULL");
                        break;
                    case 2:
                        System.out.println("\nEnter the Hospital Address to search for: ");
                        hospitalRecord.setHospitalAddress(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalAddress = '" + hospitalRecord.getHospitalAddress() + "' AND isHospitalDeleted IS NULL");
                        break;
                    case 3:
                        System.out.println("\nEnter the Hospital Number to search for: ");
                        hospitalRecord.setHospitalNum(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalNum = '" + hospitalRecord.getHospitalNum() + "' AND isHospitalDeleted IS NULL");
                        break;
                }
                bw.write(pS.toString());
                connection.setAutoCommit(false);
                try{
                    rset = pS.executeQuery();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
                System.out.println("\nThe records selected are:");
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int hospitalID = rset.getInt("hospitalID");
                    String hospitalName = rset.getString("hospitalName");
                    String hospitalAddress = rset.getString("hospitalAddress");
                    String hospitalNum = rset.getString("hospitalNum");
                    String isHospitalDeleted = rset.getString("isHospitalDeleted");
                    System.out.println(hospitalName + ", " + hospitalAddress + ", " + hospitalNum);
                }
                break;
            case 2:
                do{
                    System.out.println("\nEnter Number to Search by:\n" +
                            "1.) Doctor Name\n" +
                            "2.) Doctor Number\n" +
                            "3.) Doctor Specialty\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 4 || searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Doctor Name to search for: ");
                        hospitalRecord.setDoctorName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorName LIKE '%" + hospitalRecord.getDoctorName() + "%' AND isDoctorDeleted IS NULL");
                        break;
                    case 2:
                        System.out.println("\nEnter the Doctor Number to search for: ");
                        hospitalRecord.setDoctorNum(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorNum = '" + hospitalRecord.getDoctorNum() + "' AND isDoctorDeleted IS NULL");
                        break;
                    case 3:
                        System.out.println("\nEnter the Doctor Specialty to search for: ");
                        hospitalRecord.setDoctorSpecialty(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorSpecialty LIKE '%" + hospitalRecord.getDoctorSpecialty() + "%' AND isDoctorDeleted IS NULL");
                        break;
                }
                bw.write(pS.toString());
                connection.setAutoCommit(false);
                try{
                    rset = pS.executeQuery();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
                System.out.println("\nThe records selected are:");
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int doctorID = rset.getInt("doctorID");
                    String doctorName = rset.getString("doctorName");
                    String doctorNum = rset.getString("doctorNum");
                    String doctorSpecialty = rset.getString("doctorSpecialty");
                    int hospitalID = rset.getInt("hospitalID");
                    String isDoctorDeleted = rset.getString("isDoctorDeleted");
                    System.out.println(doctorName + ", " + doctorNum + ", " + doctorSpecialty);
                }
                break;
        }
    }
}





