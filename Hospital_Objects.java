package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//ADD Constraints for CONSTRUCTOR and SETTER Methods
public class Hospital_Objects {

    MySQL_Connection mysqlConnection = new MySQL_Connection();
    Connection connection;
    PreparedStatement pS;
    ResultSet rset;

    int hospitalID;
    String hospitalName;
    String hospitalAddress;
    String hospitalNum;
    String isHospitalDeleted;


    int serviceID;
    String serviceName;
    String medsPrescribed;
    String isServiceDeleted;

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
    String isPaymentDeleted;

    String hospitalPassword;
    String doctorPassword;
    String patientPassword;

    int zip;
    int addr;
    long num;

    public Hospital_Objects() {}
    public Hospital_Objects(String hospitalName, String hospitalAddress, String hospitalNum, String serviceName, String medsPrescribed,
                            String doctorName, String doctorNum, String doctorSpecialty, String patientName, String patientNum,
                            String patientAddress, String patientSymptoms, String paymentBy, String paymentDate)
    {
        this.hospitalName = hospitalName;
        this.hospitalAddress = hospitalAddress;
        this.hospitalNum = hospitalNum;
        this.serviceName = serviceName;
        this.medsPrescribed = medsPrescribed;
        this.doctorName = doctorName;
        this.doctorNum = doctorNum;
        this.doctorSpecialty = doctorSpecialty;
        this.patientName = patientName;
        this.patientNum = patientNum;
        this.patientAddress = patientAddress;
        this.patientSymptoms = patientSymptoms;
        this.paymentBy = paymentBy;
        this.paymentDate = paymentDate;
    }

    public int getHospitalID()
    {
        return hospitalID;
    }
    public void setHospitalID(int hospitalID)
    {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pS = connection.prepareStatement("SELECT hospitalID FROM Hospitals WHERE hospitalID = " + hospitalID);
            rset = pS.executeQuery();
            while(rset.next()) {   // Move the cursor to the next row, return false if no more row\
                if(rset.getInt("hospitalID") == hospitalID)
                {
                    this.hospitalID = hospitalID;
                }
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String getHospitalName()
    {
        return hospitalName;
    }
    public void setHospitalName(String hospitalName)
    {
        String[] splited = hospitalName.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            System.out.println("Invalid entry");
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.hospitalName = hospitalName;
        }
    }
    public String getHospitalAddress()
    {
        return hospitalAddress;
    }
    public void setHospitalAddress(String hospitalAddress)
    {
        String[] splited = hospitalAddress.split(" ");
        int i = splited.length-1;
        try{
            zip = Integer.parseInt(splited[i]);
        }catch (Exception e)
        {
            return;
        }
        if((zip/10000) >= 1)
        {
            i--;
            while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
            {
                i--;
            }
            try{
                addr = Integer.parseInt(splited[i]);
                if((addr/1) >= 1)
                {
                    this.hospitalAddress = hospitalAddress;
                }
            }catch (Exception e)
            {
                System.out.println("Invalid entry");
            }
        }
    }
    public String getHospitalNum()
    {
        return hospitalNum;
    }
    public void setHospitalNum(String hospitalNum)
    {
        try{
            num = Long.parseLong(hospitalNum);
        }catch (Exception e)
        {
            System.out.println("Invalid entry");
            return;
        }
        if((num/1000000000) >= 1 || num/1000000000 <= 9 && patientNum.length() == 10)
        {
            this.hospitalNum = hospitalNum;
        }
    }
    public String getIsHospitalDeleted()
    {
        return isHospitalDeleted;
    }
    public void setIsHospitalDeleted(String isHospitalDeleted)
    {
        this.isHospitalDeleted = isHospitalDeleted;
    }
    public int getServiceID()
    {
        return serviceID;
    }
    public void setServiceID(int serviceID)
    {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pS = connection.prepareStatement("SELECT * FROM Services WHERE serviceID = " + serviceID);
            rset = pS.executeQuery();
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                if(rset.getInt("serviceID") == serviceID)
                {
                    this.serviceID = serviceID;
                }
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String getServiceName()
    {
        return serviceName;
    }
    public void setServiceName(String serviceName)
    {
        String[] splited = serviceName.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.serviceName = serviceName;
        }
    }
    public String getMedsPrescribed()
    {
        return medsPrescribed;
    }
    public void setMedsPrescribed(String medsPrescribed)
    {
        if(medsPrescribed.equals("0") || medsPrescribed.equals("1"))
        {
            this.medsPrescribed = medsPrescribed;
        }

    }
    public String getIsServiceDeleted()
    {
        return isServiceDeleted;
    }
    public void setIsServiceDeleted(String isServiceDeleted)
    {
        this.isServiceDeleted = isServiceDeleted;
    }
    public int getDoctorID()
    {
        return doctorID;
    }
    public void setDoctorID(int doctorID)
    {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pS = connection.prepareStatement("SELECT * FROM Doctors WHERE doctorID = " + doctorID);
            rset = pS.executeQuery();
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                if(rset.getInt("doctorID") == doctorID)
                {
                    this.doctorID = doctorID;
                }
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String getDoctorName()
    {
        return doctorName;
    }
    public void setDoctorName(String doctorName)
    {
        String[] splited = doctorName.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.doctorName = doctorName;
        }

    }
    public String getDoctorNum()
    {
        return doctorNum;
    }
    public void setDoctorNum(String doctorNum)
    {
        try{
            num = Long.parseLong(doctorNum);
        }catch (Exception e)
        {
            System.out.println("Invalid entry");
            return;
        }
        if((num/1000000000) >= 1 || num/1000000000 <= 9 && patientNum.length() == 10)
        {
            this.doctorNum = doctorNum;
        }
    }
    public String getDoctorSpecialty()
    {
        return doctorSpecialty;
    }
    public void setDoctorSpecialty(String doctorSpecialty)
    {
        String[] splited = doctorSpecialty.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.doctorSpecialty = doctorSpecialty;
        }

    }
    public String getIsDoctorDeleted()
    {
        return isDoctorDeleted;
    }
    public void setIsDoctorDeleted(String isDoctorDeleted)
    {
        this.isDoctorDeleted = isDoctorDeleted;
    }
    public int getPatientID()
    {
        return patientID;
    }
    public void setPatientID(int patientID)
    {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pS = connection.prepareStatement("SELECT * FROM Patients WHERE patientID = " + patientID);
            rset = pS.executeQuery();
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                if(rset.getInt("patientID") == patientID)
                {
                    this.patientID = patientID;
                }
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String getPatientName()
    {
        return patientName;
    }
    public void setPatientName(String patientName)
    {
        String[] splited = patientName.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.patientName = patientName;
        }

    }
    public String getPatientNum()
    {
        return patientNum;
    }
    public void setPatientNum(String patientNum)
    {
        try{
            num = Long.parseLong(patientNum);
        }catch (Exception e)
        {
            System.out.println("Invalid entry");
        }
        if((num/1000000000) >= 1 || num/1000000000 <= 9 && patientNum.length() == 10)
        {
            this.patientNum = patientNum;
        }
    }
    public String getPatientAddress()
    {
        return patientAddress;
    }
    public void setPatientAddress(String patientAddress)
    {
        String[] splited = patientAddress.split(" ");
        int i = splited.length-1;
        try{
            zip = Integer.parseInt(splited[i]);
        }catch (Exception e)
        {
            System.out.println("Invalid entry");
            return;
        }
        if((zip/10000) >= 1)
        {
            i--;
            while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
            {
                i--;
            }
            try{
                addr = Integer.parseInt(splited[i]);
                if((addr/1) >= 1)
                {
                    this.patientAddress = patientAddress;
                }
            }catch (Exception e)
            {
                System.out.println("Invalid entry");
            }
        }
    }
    public String getPatientSymptoms()
    {
        return patientSymptoms;
    }
    public void setPatientSymptoms(String patientSymptoms)
    {
        String[] splited = patientSymptoms.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.patientSymptoms = patientSymptoms;
        }
    }
    public String getIsPatientDeleted()
    {
        return isPatientDeleted;
    }
    public void setIsPatientDeleted(String isPatientDeleted)
    {
        this.isPatientDeleted = isPatientDeleted;
    }
    public int getPaymentID()
    {
        return paymentID;
    }
    public void setPaymentID(int paymentID)
    {
        try {
            connection = mysqlConnection.MySQL_Connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pS = connection.prepareStatement("SELECT * FROM Payments WHERE paymentID = " + paymentID);
            rset = pS.executeQuery();
            while (rset.next()) {   // Move the cursor to the next row, return false if no more row\
                if(rset.getInt("paymentID") == paymentID)
                {
                    this.paymentID = paymentID;
                }
            }
        } catch (SQLException e) {
            return;
        }
    }
    public String getPaymentBy()
    {
        return paymentBy;
    }
    public void setPaymentBy(String paymentBy)
    {
        String[] splited = paymentBy.split(" ");
        int i = splited.length-1;
        while((!splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or")) && i > 0)
        {
            i--;
        }
        if(i > 0)
        {
            return;
        }
        else if(i == 0 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.paymentBy = paymentBy;
        }
    }
    public String getPaymentDate()
    {
        return paymentDate;
    }
    public void setPaymentDate(String paymentDate)
    {
        this.paymentDate = paymentDate;
    }
    public String getIsPaymentDeleted()
    {
        return isPaymentDeleted;
    }
    public void setIsPaymentDeleted(String isPaymentDeleted)
    {
        this.isPaymentDeleted = isPaymentDeleted;
    }
    public String getHospitalPassword()
    {
        return hospitalPassword;
    }
    public void setHospitalPassword(String hospitalPassword)
    {
        String[] splited = hospitalPassword.split(" ");
        int i = splited.length-1;
        if(i == - 1 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.hospitalPassword = hospitalPassword;
        }
        else
        {
            return;
        }
    }
    public String getDoctorPassword()
    {
        return doctorPassword;
    }
    public void setDoctorPassword(String doctorPassword)
    {
        String[] splited = doctorPassword.split(" ");
        int i = splited.length-1;
        if(i == -1 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.doctorPassword = doctorPassword;
        }
        else
        {
            return;
        }
    }
    public String getPatientPassword()
    {
        return patientPassword;
    }
    public void setPatientPassword(String patientPassword)
    {
        String[] splited = patientPassword.split(" ");
        int i = splited.length-1;
        if(i == -1 && !splited[i].contains("=") || !splited[i].contains("'") || !splited[i].contains("or"))
        {
            this.patientPassword = patientPassword;
        }
        else
        {
            return;
        }
    }
}
