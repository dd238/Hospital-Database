package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Hospital_Manager {

    Random password = new Random();
    Scanner keyboard = new Scanner(System.in);
    MySQL_Connection mysqlConnection = new MySQL_Connection();
    Hospital_Objects hospitalRecord = new Hospital_Objects();
    Connection connection;
    PreparedStatement pS;
    PreparedStatement pS1;
    PreparedStatement pS2;
    ResultSet rset;
    int display;
    String displayType;
    boolean isDefault = false;
    String sqlDisplay;
    int create;
    int update;
    int updateType;
    int delete;
    int search;
    int searchType;
    
    public void display(int hospitalID1, BufferedWriter bw) throws SQLException, IOException {


        do{
            System.out.println("\nEnter Number for Display:\n" +
                    "1.) Hospitals\n" +
                    "2.) Services\n" +
                    "3.) Doctors\n" +
                    "4.) Patients\n" +
                    "5.) Payments\n" +
                    "6.) Hospital View\n" +
                    "7.) Scripts Written\n" +
                    "8.) Patients Treated\n" +
                    "9.) Total Doctors and Patients\n");
            display = keyboard.nextInt();
            keyboard.nextLine();
            switch (display) {
                case 1:
                    sqlDisplay = "SELECT * FROM Hospitals WHERE isHospitalDeleted IS NULL";
                    isDefault = false;
                    break;
                case 2:
                    sqlDisplay = "SELECT * FROM Services WHERE isServiceDeleted IS NULL";
                    isDefault = false;
                    break;
                case 3:
                    sqlDisplay = "SELECT * FROM Doctors WHERE isDoctorDeleted IS NULL";
                    isDefault = false;
                    break;
                case 4:
                    sqlDisplay = "SELECT * FROM Patients WHERE isPatientDeleted IS NULL";
                    isDefault = false;
                    break;
                case 5:
                    sqlDisplay = "SELECT * FROM Payments WHERE isPaymentDeleted IS NULL";
                    isDefault = false;
                    break;
                case 6:
                    sqlDisplay = "SELECT * FROM HosDocSer";
                    isDefault = false;
                    break;
                case 7:
                    sqlDisplay = "SELECT * FROM TotalDocScripts";
                    isDefault = false;
                    break;
                case 8:
                    sqlDisplay = "SELECT * FROM PatPerDoc";
                    isDefault = false;
                    break;
                case 9:
                    System.out.println(hospitalRecord.getHospitalID());
                    sqlDisplay = "SELECT h.hospitalName, (SELECT COUNT(d.doctorID) FROM Doctors d" +
                            " JOIN Hospitals h ON h.hospitalID = d.hospitalID WHERE d.hospitalID = " + hospitalID1 +
                            " GROUP BY d.hospitalID) AS docTotal, (SELECT COUNT(p.patientID) FROM Patient_Doctor p JOIN Doctors d ON p.doctorID = d.doctorID" +
                            " JOIN Hospitals h ON h.hospitalID = d.doctorID WHERE h.hospitalID = " + hospitalID1 +
                            " GROUP BY h.hospitalID) AS patTotal FROM Hospitals h WHERE h.hospitalID = " + hospitalID1;
                    isDefault = false;
                    break;
                default:
                    System.out.println("Input Error.");
                    isDefault = true;
                    break;
            }
        }while (isDefault == true);


        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        pS = connection.prepareStatement(sqlDisplay);
        rset = pS.executeQuery();

        bw.write(pS.toString());


        System.out.println("\nThe records selected are:");

        //update with getter and setter methods instead of variables
        switch (display) {
            case 1:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int hospitalID = rset.getInt("hospitalID");
                    String hospitalName = rset.getString("hospitalName");
                    String hospitalAddress = rset.getString("hospitalAddress");
                    String hospitalNum = rset.getString("hospitalNum");
                    String isHospitalDeleted = rset.getString("isHospitalDeleted");
                    System.out.println(hospitalID + ", " + hospitalName + ", " + hospitalAddress + ", " + hospitalNum);
                }
                break;
            case 2:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int serviceID = rset.getInt("serviceID");
                    String serviceName = rset.getString("serviceName");
                    String medsPrescribed = rset.getString("medsPrescribed");
                    String hospitalID = rset.getString("hospitalID");
                    String isServiceDeleted = rset.getString("isServiceDeleted");
                    System.out.println(serviceID + ", " + serviceName + ", " + medsPrescribed + ", " + hospitalID);
                }
                break;
            case 3:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int doctorID = rset.getInt("doctorID");
                    String doctorName = rset.getString("doctorName");
                    String doctorNum = rset.getString("doctorNum");
                    String doctorSpecialty = rset.getString("doctorSpecialty");
                    int hospitalID = rset.getInt("hospitalID");
                    String isDoctorDeleted = rset.getString("isDoctorDeleted");
                    System.out.println(doctorID + ", " + doctorName + ", " + doctorNum + ", " + doctorSpecialty + ", " + hospitalID);
                }
                break;
            case 4:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int patientID = rset.getInt("patientID");
                    String patientName = rset.getString("patientName");
                    String patientNum = rset.getString("patientNum");
                    String patientAddress = rset.getString("patientAddress");
                    String patientSymptoms = rset.getString("patientSymptoms");
                    String isPatientDeleted = rset.getString("isPatientDeleted");
                    System.out.println(patientID + ", " + patientName + ", " + patientNum + ", " + patientAddress + ", " +
                            patientSymptoms);
                }
                break;
            case 5:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int paymentID = rset.getInt("paymentID");
                    String paymentBy = rset.getString("paymentBy");
                    String paymentDate = rset.getString("paymentDate");
                    int serviceID = rset.getInt("serviceID");
                    int patientID = rset.getInt("patientID");
                    String isPaymentDeleted = rset.getString("isPaymentDeleted");
                    System.out.println(paymentID + ", " + paymentBy + ", " + paymentDate + ", " + serviceID + ", " +
                            patientID);
                }
                break;
            case 6:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int hospitalID = rset.getInt("hospitalID");
                    String hospitalName = rset.getString("hospitalName");
                    int serviceID = rset.getInt("serviceID");
                    String serviceName = rset.getString("serviceName");
                    String medsPrescribed = rset.getString("medsPrescribed");
                    int doctorID = rset.getInt("doctorID");
                    String doctorName = rset.getString("doctorName");
                    String doctorSpecialty = rset.getString("doctorSpecialty");
                    System.out.println(hospitalID + ", " + hospitalName + ", " + serviceID + ", " + serviceName + ", " +
                            medsPrescribed + "," + doctorID + "," + doctorName + "," + doctorSpecialty);
                }
                break;
            case 7:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int doctorID = rset.getInt("doctorID");
                    String doctorName = rset.getString("doctorName");
                    String TotalMedsPrescribed = rset.getString("TotalMedsPrescribed");
                    System.out.println(doctorID + ", " + doctorName + ", " + TotalMedsPrescribed);
                }
                break;
            case 8:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int doctorID = rset.getInt("doctorID");
                    String doctorName = rset.getString("doctorName");
                    String TotalPatientsTreated = rset.getString("TotalPatientsTreated");
                    System.out.println(doctorID + ", " + doctorName + ", " + TotalPatientsTreated);
                }
                break;
            case 9:
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    String hospitalName = rset.getString("hospitalName");
                    String docTotal = rset.getString("docTotal");
                    String patTotal = rset.getString("patTotal");
                    System.out.println(hospitalName + ", " + docTotal + ", " + patTotal);
                }
                break;
            default:
                System.out.println("\nInput does not match any tables.");
                break;
        }
    }

    //Implement statements to create doctor and patient login
    public void create(int hospitalID, BufferedWriter bw) throws IOException {

        do{
            System.out.println("\nEnter Number to Create:\n" +
                    "1.) Hospitals\n" +
                    "2.) Services\n" +
                    "3.) Doctors\n" +
                    "4.) Patients\n" +
                    "5.) Payments\n");
            create = keyboard.nextInt();
            keyboard.nextLine();
            if (create < 6 || create > 0) {
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

        switch (create) {
            //need to update to ensure generated keys are obtained
            case 1:
                System.out.println("\nEnter the Hospital Name: ");
                hospitalRecord.setHospitalName(keyboard.nextLine());
                System.out.println("\nEnter the Hospital Address: ");
                hospitalRecord.setHospitalAddress(keyboard.nextLine());
                System.out.println("\nEnter the Hospital Number: ");
                hospitalRecord.setHospitalNum(keyboard.nextLine());
                System.out.print(hospitalRecord.getHospitalName() + "," + hospitalRecord.getHospitalAddress() + "," + hospitalRecord.getHospitalNum());

                try {
                    pS = connection.prepareStatement("INSERT INTO Hospitals(hospitalName, hospitalAddress, hospitalNum) VALUES (?,?,?)", pS.RETURN_GENERATED_KEYS);
                    pS.setString(1, hospitalRecord.getHospitalName());
                    pS.setString(2, hospitalRecord.getHospitalAddress());
                    pS.setString(3, hospitalRecord.getHospitalNum());

                    connection.setAutoCommit(false); // No commit per statement
                    try{
                        pS.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }

                    rset = pS.getGeneratedKeys();
                    if (rset != null && rset.next()) {
                        hospitalRecord.setHospitalID(rset.getInt(1));
                    }


                    pS1 = connection.prepareStatement("INSERT INTO Hospital_Login(hospitalID, hospitalPassword) VALUES (?,?)");
                    pS1.setInt(1, hospitalRecord.getHospitalID());
                    pS1.setString(2, Integer.toString(password.nextInt(1000000)));
                    connection.setAutoCommit(false); // No commit per statement
                    try{
                        pS1.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


                bw.write(pS.toString());
                bw.write(pS1.toString());


                break;

            case 2:
                System.out.println("\nEnter the Service Name: ");
                hospitalRecord.setServiceName(keyboard.nextLine());
                System.out.println("\nMeds Prescribed (0/1): ");
                hospitalRecord.setMedsPrescribed(keyboard.nextLine());
                System.out.println("\nEnter the Doctor ID: ");
                hospitalRecord.setDoctorID(keyboard.nextInt());
                keyboard.nextLine();


                try {
                    pS = connection.prepareStatement("INSERT INTO Services(serviceName, medsPrescribed, hospitalID)" +
                            " VALUES (?,?,?)", pS.RETURN_GENERATED_KEYS);
                    pS.setString(1, hospitalRecord.getServiceName());
                    pS.setString(2, hospitalRecord.getMedsPrescribed());
                    pS.setInt(3, hospitalID);

                    connection.setAutoCommit(false); // No commit per statement
                    try{
                        pS.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }

                    rset = pS.getGeneratedKeys();
                    if (rset != null && rset.next()) {
                        hospitalRecord.setServiceID(rset.getInt(1));
                    }

                    pS1 = connection.prepareStatement("INSERT INTO Doctor_Services(serviceID, doctorID) VALUES (?,?)");
                    pS1.setInt(1, hospitalRecord.getServiceID());
                    pS1.setInt(2, hospitalRecord.getDoctorID());

                    connection.setAutoCommit(false);
                    try{
                        pS1.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }


                bw.write(pS.toString());
                bw.write(pS1.toString());


                break;

            case 3:
                System.out.println("\nEnter the Doctor Name: ");
                hospitalRecord.setDoctorName(keyboard.nextLine());
                System.out.println("\nEnter the Doctor Number: ");
                hospitalRecord.setDoctorNum(keyboard.nextLine());
                System.out.println("\nEnter the Doctor Specialty: ");
                hospitalRecord.setDoctorSpecialty(keyboard.nextLine());
                System.out.println("\nEnter the Hospital ID: ");
                hospitalRecord.setHospitalID(keyboard.nextInt());

                try {
                    pS = connection.prepareStatement("INSERT INTO Doctors(doctorName, doctorNum, doctorSpecialty, hospitalID) " +
                            "VALUES (?,?,?,?,?)", pS.RETURN_GENERATED_KEYS);
                    pS.setString(1, hospitalRecord.getDoctorName());
                    pS.setString(2, hospitalRecord.getDoctorNum());
                    pS.setString(3, hospitalRecord.getDoctorSpecialty());
                    pS.setInt(4, hospitalRecord.getHospitalID());

                    connection.setAutoCommit(false); // No commit per statement
                    try{
                        pS.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }

                    rset = pS.getGeneratedKeys();
                    if (rset != null && rset.next()) {
                        hospitalRecord.setDoctorID(rset.getInt(1));
                    }

                    pS1 = connection.prepareStatement("INSERT INTO Doctor_Login(doctorID, doctorPassword)" +
                            " VALUES (?,?)");
                    pS1.setInt(1, hospitalRecord.getDoctorID());
                    pS1.setString(2, Integer.toString(password.nextInt(1000000)));

                    connection.setAutoCommit(false);
                    try{
                        pS1.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                bw.write(pS.toString());
                bw.write(pS1.toString());

                break;

            case 4:
                System.out.println("\nEnter the Patient Name: ");
                hospitalRecord.setPatientName(keyboard.nextLine());
                System.out.println("\nEnter the Patient Number: ");
                hospitalRecord.setPatientNum(keyboard.nextLine());
                System.out.println("\nEnter the Patient Address: ");
                hospitalRecord.setPatientAddress(keyboard.nextLine());
                System.out.println("\nEnter the Patient Symptoms: ");
                hospitalRecord.setPatientSymptoms(keyboard.nextLine());
                System.out.println("\nEnter the Doctor ID: ");
                hospitalRecord.setDoctorID(keyboard.nextInt());
                keyboard.nextLine();

                try {
                    pS = connection.prepareStatement("INSERT INTO Patients(patientName, patientNum, patientAddress, " +
                            "patientSymptoms) VALUES (?,?,?,?)", pS.RETURN_GENERATED_KEYS);

                    pS.setString(1, hospitalRecord.getPatientName());
                    pS.setString(2, hospitalRecord.getPatientNum());
                    pS.setString(3, hospitalRecord.getPatientAddress());
                    pS.setString(4, hospitalRecord.getPatientSymptoms());

                    connection.setAutoCommit(false); // No commit per statement
                    try{
                        pS.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }

                    rset = pS.getGeneratedKeys();
                    if (rset != null && rset.next()) {
                        hospitalRecord.setPatientID(rset.getInt(1));
                    }
                    System.out.println(hospitalRecord.getPatientID());

                    pS1 = connection.prepareStatement("INSERT INTO Patient_Doctor(patientID, doctorID)" +
                            " VALUES (?,?)");
                    pS1.setInt(1, hospitalRecord.getPatientID());
                    pS1.setInt(2, hospitalRecord.getDoctorID());

                    connection.setAutoCommit(false);
                    try{
                        pS1.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                    }

                    pS2 = connection.prepareStatement("INSERT INTO Patient_Login(patientID, patientPassword)" +
                            " VALUES (?,?)");
                    pS2.setInt(1, hospitalRecord.getPatientID());
                    pS2.setString(2, Integer.toString(password.nextInt(1000000)));

                    connection.setAutoCommit(false);
                    try{
                        pS2.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                bw.write(pS.toString());
                bw.write(pS1.toString());
                bw.write(pS2.toString());

                break;

            //check that patient and service id exist
            case 5:
                System.out.println("\nEnter the Payment By: ");
                hospitalRecord.setPaymentBy(keyboard.nextLine());
                System.out.println("\nEnter the Payment Date \"MM-dd-yy-hh.mm.ss\": ");
                hospitalRecord.setPaymentDate(keyboard.nextLine());
                System.out.println("\nEnter the Service ID: ");
                hospitalRecord.setServiceID(keyboard.nextInt());
                System.out.println("\nEnter the Patient ID: ");
                hospitalRecord.setPatientID(keyboard.nextInt());

                try {
                    pS = connection.prepareStatement("INSERT INTO Payments(paymentBy, paymentDate, serviceID," +
                            " patientID) VALUES (?,?,?,?)");
                    pS.setString(1, hospitalRecord.getPaymentBy());
                    pS.setString(2, hospitalRecord.getPaymentDate());
                    pS.setInt(3, hospitalRecord.getServiceID());
                    pS.setInt(4, hospitalRecord.getPatientID());
                    try{
                        pS.executeUpdate();
                        connection.commit();
                    } catch (SQLException e) {
                        connection.rollback();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                bw.write(pS.toString());

                break;
        }
    }

    public void update(BufferedWriter bw) throws SQLException, IOException {

        do{
            System.out.println("\nEnter Number to Update:\n" +
                    "1.) Hospitals\n" +
                    "2.) Services\n" +
                    "3.) Doctors\n" +
                    "4.) Patients\n" +
                    "5.) Payments\n");
            update = keyboard.nextInt();
            if (update < 6 || update > 0) {
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

        switch (update) {
            //need to update to ensure generated keys are obtained
            case 1:
                System.out.println("\nEnter the Hospital ID of the Hospital you wish to update: ");
                hospitalRecord.setHospitalID(keyboard.nextInt());
                do{
                    System.out.println("\nWould you like to update the Hospital's:\n" +
                            "1.) Name\n" +
                            "2.) Address\n" +
                            "3.) Number\n" +
                            "4.) Password\n");
                    updateType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (updateType < 5 || updateType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (updateType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Hospital Name ");
                        hospitalRecord.setHospitalName(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Hospitals SET hospitalName = '" + hospitalRecord.getHospitalName() +
                                "' WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Hospital Address: ");
                        hospitalRecord.setHospitalAddress(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Hospitals SET hospitalAddress = '" + hospitalRecord.getHospitalAddress() +
                                "' WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                    case 3:
                        System.out.println("\nEnter the Hospital Number: ");
                        hospitalRecord.setHospitalNum(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Hospitals SET hospitalNum = " + hospitalRecord.getHospitalNum() +
                                " WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                    case 4:
                        System.out.println("\nEnter the Hospital Password: ");
                        hospitalRecord.setHospitalPassword(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Hospital_Login SET hospitalPassword = " + hospitalRecord.getHospitalPassword() +
                                " WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                    default:
                        System.out.println("Input Error.");
                        break;
                }
                break;

            case 2:
                System.out.println("\nEnter the Service ID of the Service you wish to update: ");
                hospitalRecord.setServiceID(keyboard.nextInt());
                do{
                    System.out.println("\nWould you like to update the Service's:\n" +
                            "1.) Name\n" +
                            "2.) Medicine Prescribed\n" +
                            "3.) Hospital ID\n");
                    updateType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (updateType < 4 || updateType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (updateType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Service Name ");
                        hospitalRecord.setServiceName(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Services SET serviceName = '" + hospitalRecord.getServiceName() +
                                "' WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Medicine Prescribed (0/1): ");
                        hospitalRecord.setMedsPrescribed(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Services SET medsPrescribed = '" + hospitalRecord.getMedsPrescribed() +
                                "' WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    case 3:
                        System.out.println("\nEnter the Hospital ID: ");
                        hospitalRecord.setHospitalID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Services SET hospitalID = " + hospitalRecord.getHospitalID() +
                                " WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    default:
                        System.out.println("\nInput Error.");
                        break;
                }
                break;

            case 3:
                System.out.println("\nEnter the Doctor ID of the Doctor you wish to update: ");
                hospitalRecord.setDoctorID(keyboard.nextInt());
                do{
                    System.out.println("\nWould you like to update the Doctor's:\n" +
                            "1.) Name\n" +
                            "2.) Number\n" +
                            "3.) Specialty\n" +
                            "4.) Service ID\n" +
                            "5.) Hospital ID\n" +
                            "6.) Password\n");
                    updateType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (updateType < 7 || updateType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (updateType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Doctor Name ");
                        hospitalRecord.setDoctorName(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Doctors SET doctorName = '" + hospitalRecord.getDoctorName() +
                                "' WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Doctor Number: ");
                        hospitalRecord.setDoctorNum(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Doctors SET doctorNum = '" + hospitalRecord.getDoctorNum() +
                                "' WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 3:
                        System.out.println("\nEnter the Doctor Specialty: ");
                        hospitalRecord.setDoctorSpecialty(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Doctors SET doctorSpecialty = '" + hospitalRecord.getDoctorSpecialty() +
                                "' WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 4:
                        System.out.println("\nEnter the Service ID: ");
                        hospitalRecord.setServiceID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Doctor_Services SET serviceID = " + hospitalRecord.getServiceID() +
                                " WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 5:
                        System.out.println("\nEnter the Hospital ID: ");
                        hospitalRecord.setHospitalID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Doctors SET hospitalID = " + hospitalRecord.getHospitalID() +
                                " WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 6:
                        System.out.println("\nEnter the Doctor Password: ");
                        hospitalRecord.setDoctorPassword(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Doctor_Login SET doctorPassword = " + hospitalRecord.getDoctorPassword() +
                                " WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    default:
                        System.out.println("\nInput Error.");
                        break;
                }
                break;

            case 4:
                System.out.println("\nEnter the Patient ID of the Patient you wish to update: ");
                hospitalRecord.setPatientID(keyboard.nextInt());
                do{
                    System.out.println("\nWould you like to update the Patient's:\n" +
                            "1.) Name\n" +
                            "2.) Number\n" +
                            "3.) Address\n" +
                            "4.) Symptoms\n" +
                            "5.) Doctor ID\n" +
                            "6.) Password\n");
                    updateType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (updateType < 7 || updateType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (updateType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Patient Name ");
                        hospitalRecord.setPatientName(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Patients SET patientName = '" + hospitalRecord.getPatientName() +
                                "' WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Patient Number: ");
                        hospitalRecord.setPatientNum(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Patients SET patientNum = '" + hospitalRecord.getPatientNum() +
                                "' WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 3:
                        System.out.println("\nEnter the Patient Address: ");
                        hospitalRecord.setPatientAddress(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Patients SET patientAddress = '" + hospitalRecord.getPatientAddress() +
                                "' WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 4:
                        System.out.println("\nEnter the Patient Symptoms: ");
                        hospitalRecord.setPatientSymptoms(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Patients SET patientSymptoms = '" + hospitalRecord.getPatientSymptoms() +
                                "' WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 5:
                        System.out.println("\nEnter the Doctor ID: ");
                        hospitalRecord.setDoctorID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Patient_Doctor SET doctorID = " + hospitalRecord.getDoctorID() +
                                " WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 6:
                        System.out.println("\nEnter the Patient Password: ");
                        hospitalRecord.setPatientPassword(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Patient_Login SET patientPassword = " + hospitalRecord.getPatientPassword() +
                                " WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    default:
                        System.out.println("\nInput Error.");
                        break;
                }
                break;

            case 5:
                System.out.println("\nEnter the Payment ID of the Payment you wish to update: ");
                hospitalRecord.setPaymentID(keyboard.nextInt());
                do{
                    System.out.println("\nWould you like to update the Payment's:\n" +
                            "1.) Payment By\n" +
                            "2.) Payment Date\n" +
                            "3.) Service ID\n" +
                            "4.) Patient ID\n");
                    updateType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (updateType < 5 || updateType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (updateType) {
                    case 1:
                        System.out.println("\nEnter the Payer Name ");
                        hospitalRecord.setPaymentBy(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Payments SET paymentBy = '" + hospitalRecord.getPaymentBy() +
                                "' WHERE paymentID = " + hospitalRecord.getPaymentID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Payment Date (MM-dd-yy-hh.mm.ss): ");
                        hospitalRecord.setPatientNum(keyboard.nextLine());
                        pS = connection.prepareStatement("UPDATE Payments SET paymentDate = '" + hospitalRecord.getPaymentDate() +
                                "' WHERE paymentID = " + hospitalRecord.getPaymentID());
                        break;
                    //check to see if service id exists
                    case 3:
                        System.out.println("\nEnter the Service ID: ");
                        hospitalRecord.setServiceID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Payments SET serviceID = " + hospitalRecord.getServiceID() +
                                " WHERE paymentID = " + hospitalRecord.getPaymentID());
                        break;
                    //check to see if patient exists
                    case 4:
                        System.out.println("\nEnter the Patient ID: ");
                        hospitalRecord.setPatientID(keyboard.nextInt());
                        pS = connection.prepareStatement("UPDATE Payments SET patientID = " + hospitalRecord.getPatientID() +
                                " WHERE paymentID = " + hospitalRecord.getPaymentID());
                        break;
                    default:
                        System.out.println("\nInput Error.");
                        break;
                }
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

    public void delete(BufferedWriter bw) throws SQLException, IOException {

        do{
            System.out.println("\nEnter Number to Delete:\n" +
                    "1.) Hospitals\n" +
                    "2.) Services\n" +
                    "3.) Doctors\n" +
                    "4.) Patients\n" +
                    "5.) Payments\n");
            delete = keyboard.nextInt();
            if (delete < 6 || delete > 0) {
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

        switch (delete) {
            //need to update to ensure generated keys are obtained
            case 1:
                System.out.println("\nEnter the Hospital ID of the Hospital you wish to delete: ");
                hospitalRecord.setHospitalID(keyboard.nextInt());

                pS = connection.prepareStatement("UPDATE Hospitals SET isHospitalDeleted = 1" +
                        " WHERE hospitalID = " + hospitalRecord.getHospitalID());
                break;
            case 2:
                System.out.println("\nEnter the Service ID of the Service you wish to delete: ");
                hospitalRecord.setServiceID(keyboard.nextInt());

                pS = connection.prepareStatement("UPDATE Services SET isServiceDeleted = 1" +
                        " WHERE serviceID = " + hospitalRecord.getServiceID());
                break;
            case 3:
                System.out.println("\nEnter the Doctor ID of the Doctor you wish to delete: ");
                hospitalRecord.setDoctorID(keyboard.nextInt());

                pS = connection.prepareStatement("UPDATE Doctors SET isDoctorDeleted = 1" +
                        " WHERE doctorID = " + hospitalRecord.getDoctorID());
                break;
            case 4:
                System.out.println("\nEnter the Patient ID of the Patient you wish to delete: ");
                hospitalRecord.setPatientID(keyboard.nextInt());

                pS = connection.prepareStatement("UPDATE Patients SET isPatientDeleted = 1" +
                        " WHERE patientID = " + hospitalRecord.getPatientID());
                break;
            case 5:
                System.out.println("\nEnter the Payment ID of the Payment you wish to delete: ");
                hospitalRecord.setPaymentID(keyboard.nextInt());

                pS = connection.prepareStatement("UPDATE Payments SET isPaymentDeleted = 1" +
                        " WHERE paymentID = " + hospitalRecord.getPaymentID());
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

    public void search(BufferedWriter bw) throws SQLException, IOException {

        do{
            System.out.println("\nEnter Number to Search:\n" +
                    "1.) Hospitals\n" +
                    "2.) Services\n" +
                    "3.) Doctors\n" +
                    "4.) Patients\n" +
                    "5.) Payments\n");
            search = keyboard.nextInt();
            if (search < 6 || search > 0) {
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
                            "1.) Hospital ID\n" +
                            "2.) Hospital Name\n" +
                            "3.) Hospital Address\n" +
                            "4.) Hospital Number\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 5|| searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Hospital ID to search for: ");
                        hospitalRecord.setHospitalID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Hospital Name to search for: ");
                        hospitalRecord.setHospitalName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalName LIKE '%" + hospitalRecord.getHospitalName() + "%'");
                        break;
                    case 3:
                        System.out.println("\nEnter the Hospital Address to search for: ");
                        hospitalRecord.setHospitalAddress(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalAddress = '" + hospitalRecord.getHospitalAddress() + "'");
                        break;
                    case 4:
                        System.out.println("\nEnter the Hospital Number to search for: ");
                        hospitalRecord.setHospitalNum(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Hospitals WHERE hospitalNum = '" + hospitalRecord.getHospitalNum() + "'");
                        break;
                }

                bw.write(pS.toString());

                connection.setAutoCommit(false); // No commit per statement
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
                    System.out.println(hospitalID + ", " + hospitalName + ", " + hospitalAddress + ", " + hospitalNum);
                }
                break;
            case 2:
                do{
                    System.out.println("\nEnter Number to Display by:\n" +
                            "1.) Service ID\n" +
                            "2.) Service Name\n" +
                            "3.) Medicine Prescribed\n" +
                            "4.) Hospital ID\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 5 || searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Service ID to search for: ");
                        hospitalRecord.setServiceID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Services WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Service Name to search for: ");
                        hospitalRecord.setServiceName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Services WHERE serviceName LIKE '%" + hospitalRecord.getServiceName() + "%'");
                        break;
                    case 3:
                        System.out.println("\nEnter the Medicine Prescribed (0/1) to search for: ");
                        hospitalRecord.setMedsPrescribed(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Services WHERE medsPrescribed = '" + hospitalRecord.getMedsPrescribed() + "'");
                        break;
                    case 4:
                        System.out.println("\nEnter the Hospital ID to search for: ");
                        hospitalRecord.setHospitalID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Services WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                }

                bw.write(pS.toString());

                connection.setAutoCommit(false); // No commit per statement
                try{
                    rset = pS.executeQuery();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
                System.out.println("\nThe records selected are:");
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int serviceID = rset.getInt("serviceID");
                    String serviceName = rset.getString("serviceName");
                    String medsPrescribed = rset.getString("medsPrescribed");
                    String hospitalID = rset.getString("hospitalID");
                    String isServiceDeleted = rset.getString("isServiceDeleted");
                    System.out.println(serviceID + ", " + serviceName + ", " + medsPrescribed + ", " + hospitalID);
                }
                break;
            case 3:
                do{
                    System.out.println("\nEnter Number to Display by:\n" +
                            "1.) Doctor ID\n" +
                            "2.) Doctor Name\n" +
                            "3.) Doctor Number\n" +
                            "4.) Doctor Specialty\n" +
                            "5.) Service ID\n" +
                            "6.) Hospital ID\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 7 || searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Doctor ID to search for: ");
                        hospitalRecord.setDoctorID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorID = " + hospitalRecord.getDoctorID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Doctor Name to search for: ");
                        hospitalRecord.setDoctorName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorName LIKE '%" + hospitalRecord.getDoctorName() + "%'");
                        break;
                    case 3:
                        System.out.println("\nEnter the Doctor Number to search for: ");
                        hospitalRecord.setDoctorNum(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorNum = '" + hospitalRecord.getDoctorNum() + "'");
                        break;
                    case 4:
                        System.out.println("\nEnter the Doctor Specialty to search for: ");
                        hospitalRecord.setDoctorSpecialty(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorSpecialty LIKE '%" + hospitalRecord.getDoctorSpecialty() + "%'");
                        break;
                    case 5:
                        System.out.println("\nEnter the Service ID to search for: ");
                        hospitalRecord.setServiceID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Doctor_Services WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    case 6:
                        System.out.println("\nEnter the Hospital ID to search for: ");
                        hospitalRecord.setHospitalID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Doctors WHERE hospitalID = " + hospitalRecord.getHospitalID());
                        break;
                }
                bw.write(pS.toString());

                connection.setAutoCommit(false); // No commit per statement
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
                    int serviceID = rset.getInt("serviceID");
                    int hospitalID = rset.getInt("hospitalID");
                    String isDoctorDeleted = rset.getString("isDoctorDeleted");
                    System.out.println(doctorID + ", " + doctorName + ", " + doctorNum + ", " + doctorSpecialty + ", " + serviceID + ", " + hospitalID);
                }
                break;
            case 4:
                do{
                    System.out.println("\nEnter Number to Display by:\n" +
                            "1.) Patient ID\n" +
                            "2.) Patient Name\n" +
                            "3.) Patient Number\n" +
                            "4.) Patient Address\n" +
                            "5.) Patient Symptoms");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 6 || searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Patient ID to search for: ");
                        hospitalRecord.setPatientID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Patient Name to search for: ");
                        hospitalRecord.setPatientName(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientName LIKE '%" + hospitalRecord.getPatientName() + "%'");
                        break;
                    case 3:
                        System.out.println("\nEnter the Patient Number to search for: ");
                        hospitalRecord.setPatientNum(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientNum = '" + hospitalRecord.getPatientNum() + "'");
                        break;
                    case 4:
                        System.out.println("\nEnter the Patient Address to search for: ");
                        hospitalRecord.setPatientAddress(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientAddress = '" + hospitalRecord.getPatientAddress() + "'");
                        break;
                    case 5:
                        System.out.println("\nEnter the Patient Symptoms to search for: ");
                        hospitalRecord.setPatientSymptoms(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientSymptoms LIKE '%" + hospitalRecord.getPatientSymptoms() + "%'");
                        break;
                }
                bw.write(pS.toString());

                connection.setAutoCommit(false); // No commit per statement
                try{
                    rset = pS.executeQuery();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
                System.out.println("\nThe records selected are:");
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int patientID = rset.getInt("patientID");
                    String patientName = rset.getString("patientName");
                    String patientNum = rset.getString("patientNum");
                    String patientAddress = rset.getString("patientAddress");
                    String patientSymptoms = rset.getString("patientSymptoms");
                    String isPatientDeleted = rset.getString("isPatientDeleted");
                    System.out.println(patientID + ", " + patientName + ", " + patientNum + ", " + patientAddress + ", " +
                            patientSymptoms);
                }
                break;
            case 5:
                do{
                    System.out.println("\nEnter Number to Display by:\n" +
                            "1.) Payment ID\n" +
                            "2.) Payment By\n" +
                            "3.) Payment Date\n" +
                            "4.) Service ID\n" +
                            "5.) Patient ID\n");
                    searchType = keyboard.nextInt();
                    keyboard.nextLine();
                    if (searchType < 6 || searchType > 0) {
                        isDefault = false;
                    } else {
                        isDefault = true;
                    }
                }while (isDefault == true);

                switch (searchType) {
                    //need to update to ensure generated keys are obtained
                    case 1:
                        System.out.println("\nEnter the Payment ID to search for: ");
                        hospitalRecord.setPaymentID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Payments WHERE paymentID = " + hospitalRecord.getPaymentID());
                        break;
                    case 2:
                        System.out.println("\nEnter the Payer Name to search for: ");
                        hospitalRecord.setPaymentBy(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Payments WHERE paymentBy = '" + hospitalRecord.getPaymentBy() + "'");
                        break;
                    case 3:
                        System.out.println("\nEnter the Payment Date to search for: ");
                        hospitalRecord.setPaymentDate(keyboard.nextLine());
                        pS = connection.prepareStatement("SELECT * FROM Payments WHERE paymentDate = '" + hospitalRecord.getPaymentDate() + "'");
                        break;
                    case 4:
                        System.out.println("\nEnter the Service ID to search for: ");
                        hospitalRecord.setServiceID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Payments WHERE serviceID = " + hospitalRecord.getServiceID());
                        break;
                    case 5:
                        System.out.println("\nEnter the Patient ID to search for: ");
                        hospitalRecord.setPaymentID(keyboard.nextInt());
                        pS = connection.prepareStatement("SELECT * FROM Payments WHERE patientID = " + hospitalRecord.getPatientID());
                        break;
                }
                bw.write(pS.toString());

                connection.setAutoCommit(false); // No commit per statement
                try{
                    rset = pS.executeQuery();
                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                }
                System.out.println("\nThe records selected are:");
                while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                    int paymentID = rset.getInt("paymentID");
                    String paymentBy = rset.getString("paymentBy");
                    String paymentDate = rset.getString("paymentDate");
                    int serviceID = rset.getInt("serviceID");
                    int patientID = rset.getInt("patientID");
                    String isPaymentDeleted = rset.getString("isPaymentDeleted");
                    System.out.println(patientID + ", " + paymentBy + ", " + paymentDate + ", " + serviceID + ", " +
                            patientID);
                }
                break;
        }
    }
}
