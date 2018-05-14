package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    int doctorID;

    int patientID;

    int option;

    FileWriter fw;
    BufferedWriter bw;

    public DB_Manager() throws IOException {
    }

    public void verifyAdmin(int hospitalID, String hospitalPassword) throws SQLException, IOException {
        fw = new FileWriter("hospitalLog.log");
        bw = new BufferedWriter(fw);

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

        bw.write(pS.toString());

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
                        hosManager.display(hospitalID, bw);
                        break;
                    case 2:
                        hosManager.create(hospitalID, bw);
                        break;
                    case 3:
                        hosManager.update(bw);
                        break;
                    case 4:
                        hosManager.delete(bw);
                        break;
                    case 5:
                        hosManager.search(bw);
                        break;
                    case 6:
                        isLoggedIn = false;
                        bw.close();
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }
        }while(isLoggedIn == true);
    }

    public void verifyDoctor(int doctorID, String doctorPassword) throws SQLException, IOException {
        fw = new FileWriter("doctorLog.log");
        bw = new BufferedWriter(fw);
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

        bw.write(pS.toString());

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
                        docManager.display(doctorID, bw);
                        break;
                    case 2:
                        docManager.create(doctorID, bw);
                        break;
                    case 3:
                        docManager.update(doctorID, bw);
                        break;
                    case 4:
                        docManager.delete(bw);
                        break;
                    case 5:
                        docManager.search(bw);
                        break;
                    case 6:
                        isLoggedIn = false;
                        bw.close();
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }

        }while(isLoggedIn == true);

    }
    public void verifyPatient(int patientID, String patientPassword) throws SQLException, IOException {
        fw = new FileWriter("patientLog.log");
        bw = new BufferedWriter(fw);
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

        bw.write(pS.toString());

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
                        patManager.display(patientID, bw);
                        break;
                    case 2:
                        patManager.update(patientID, bw);
                        break;
                    case 3:
                        patManager.delete(patientID, bw);
                        break;
                    case 4:
                        patManager.search(patientID, bw);
                        break;
                    case 5:
                        isLoggedIn = false;
                        bw.close();
                        break;
                    default:
                        System.out.println("Invalid Entry");
                        break;
                }

        }while(isLoggedIn == true);

    }
}
