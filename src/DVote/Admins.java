package DVote;

import Essentials.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Admins
{
    static String status;
    static Scanner obj=new Scanner(System.in);
    static Connection con;
    static{
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/DVoteIndia", "root", "qwertyuiop1234");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void adminRights()
    {
        System.out.println("Welcome ADMIN");
        System.out.println("What Would You Like to Do?");
        System.out.println("1) ADD CANDIDATE DETAILS");
        System.out.println("2) ADD VOTER DETAILS");
        System.out.println("3) START ELECTIONS");
        System.out.println("4) END ELECTIONS");
        System.out.println("5) EXIT");
        String reply=obj.nextLine();
        reply=reply.toLowerCase().trim(); //so that the contains works well(and removing prev whitespace)

        if(reply.equals("1") || reply.equalsIgnoreCase("add candidate") || reply.contains("candidate"))
        {
            System.out.print("Enter Candidate Name: ");
            String candName=obj.nextLine();

            System.out.print("Enter Candidate Party: ");
            String candParty=obj.nextLine();

            System.out.print("Enter Candidate Region: ");
            String candRegion=obj.nextLine();

            String query="INSERT INTO candidateDatabase (regionCode, candidateName, candidateParty) VALUES(?, ?, ?)";

            try(PreparedStatement pstmt=con.prepareStatement(query))
            {
                pstmt.setString(1,candRegion);
                pstmt.setString(2, candName);
                pstmt.setString(3, candParty);
                pstmt.executeUpdate();
                System.out.println("Added SUCCESSFULLY!\n\n");
                adminRights();
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(reply.equals("2") || reply.equalsIgnoreCase("add voter") || reply.contains("voter"))
        {
            System.out.print("Enter Voter Name: ");
            String voterName=obj.nextLine();

            System.out.print("Enter Voter Aadhar Number: ");
            String voterAadhar=obj.nextLine();

            System.out.print("Enter Voter Mobile No: ");
            String voterMobNo=obj.nextLine();

            System.out.print("Enter Voter Region: ");
            String voterRegion=obj.nextLine();

            String query1="INSERT INTO voterDatabase (voterName, aadharNumber, mobNo, regionCode, voteStatus) VALUES(?,?,?,?,?)";

            try(PreparedStatement pstmt=con.prepareStatement(query1))
            {
                pstmt.setString(1, voterName);
                pstmt.setString(2, voterAadhar);
                pstmt.setString(3, voterMobNo);
                pstmt.setString(4, voterRegion);
                pstmt.setString(5, "NOT VOTED");
                pstmt.executeUpdate();
                System.out.println("Added SUCCESSFULLY!\n\n");
                adminRights();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else if(reply.equals("3") || reply.equalsIgnoreCase("start election") || reply.contains("start"))
        {
            status="START";
        }
        else if(reply.equals("4") || reply.equalsIgnoreCase("end election") || reply.contains("end"))
        {
            status="END";
        }
        else if(reply.equals("5") || reply.equalsIgnoreCase("exit") || reply.contains("xit"))
        {
            System.out.println("EXITING. . . . ");
            System.exit(0);
        }
        else {
            System.exit(0);
        }
    }

//    public static void setStatus(String presentStatus)
//    {
//        if(presentStatus.equals("START"))
//        {
//            ElectionStatus();
//        }
//        else {
//            ElectionStatus();
//        }
//    }

    public static boolean ElectionStatus()
    {
        String state=status;
        if(state.equals("START"))
        {
            return true;
        }
        else {
            return false;
        }
    }

}
