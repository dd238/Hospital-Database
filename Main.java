package com.company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Scanner keyboard = new Scanner(System.in);
        CSV_Creator csv_creator = new CSV_Creator();
        CSV_Reader csv_reader = new CSV_Reader();


        Hospital_Objects hospitalRecord = new Hospital_Objects();
        DB_Manager dbManager = new DB_Manager();

        String fileName;
        int nTuples;

        int loginType;


        int hospitalID;
        String hospitalPW;
        String hospitalName;
        String hospitalAddress;
        String hospitalNum;

        int serviceID;
        String serviceName;
        String medsPrescribed;

        int doctorID;
        String doctorPW;
        String doctorName;
        String doctorNum;
        String doctorSpecialty;

        int patientID;
        String patientName;
        String patientNum;
        String patientAddress;
        String patientSymptoms;

        int paymentID;
        String patientPW;
        String paymentBy;
        String paymentDate;


        String userID;
        int i = 0;

        System.out.println("Welcome to Health Services Solutions the Premier Leader is Health Record Management\n" +
                "\nPlease Select your appropriate login, for new users please register:\n" +
                "1.) Patient\n" +
                "2.) Doctor\n" +
                "3.) Health Admin\n");

        loginType = keyboard.nextInt();

        switch (loginType) {
            case 1:
                try {
                    System.out.println("\nEnter the Patient ID: ");
                    hospitalRecord.setPatientID(keyboard.nextInt());
                    keyboard.nextLine();
                    System.out.println("\nEnter the Patient Password: ");
                    hospitalRecord.setPatientPassword(keyboard.nextLine());
                    dbManager.verifyPatient(hospitalRecord.getPatientID(), hospitalRecord.getPatientPassword());
                } catch (Exception e) {
                    System.out.println("\nUsername or Password Invalid");
                }
                break;
            case 2:
                try {
                    System.out.println("\nEnter the Doctor ID: ");
                    hospitalRecord.setDoctorID(keyboard.nextInt());
                    keyboard.nextLine();
                    System.out.println("\nEnter the Doctor Password: ");
                    hospitalRecord.setDoctorPassword(keyboard.nextLine());
                    dbManager.verifyDoctor(hospitalRecord.getDoctorID(), hospitalRecord.getDoctorPassword());
                } catch (Exception e) {
                    System.out.println("\nUsername or Password Invalid");
                }
                break;
            case 3:
                try {
                    System.out.println("\nEnter the Hospital ID: ");
                    hospitalRecord.setHospitalID(keyboard.nextInt());
                    keyboard.nextLine();
                    System.out.println("\nEnter the Admin Password: ");
                    hospitalRecord.setHospitalPassword(keyboard.nextLine());
                    dbManager.verifyAdmin(hospitalRecord.getHospitalID(), hospitalRecord.getHospitalPassword());
                } catch (Exception e) {
                    System.out.println("\nUsername or Password Invalid");
                }
                break;
            default:
                System.out.println("\nInput Error.");
                break;
        }
    }
}
