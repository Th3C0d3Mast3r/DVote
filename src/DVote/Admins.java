package DVote;

import Essentials.*;
import java.sql.*;
import java.util.*;

public class Admins {
    static Scanner obj = new Scanner(System.in);
    static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DVoteIndia", "root", "qwertyuiop1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void adminRights() {
        System.out.println("Welcome ADMIN");
        System.out.println("What Would You Like to Do?");
        System.out.println("1) ADD CANDIDATE DETAILS");
        System.out.println("2) ADD VOTER DETAILS");
        System.out.println("3) START ELECTIONS");
        System.out.println("4) END ELECTIONS");
        System.out.println("5) EXIT");
        String reply = obj.nextLine();
        reply = reply.toLowerCase().trim(); // so that the contains works well(and removing prev whitespace)

        if (reply.equals("1") || reply.equalsIgnoreCase("add candidate") || reply.contains("candidate")) {
            System.out.print("Enter Candidate Name: ");
            String candName = obj.nextLine();

            System.out.print("Enter Candidate Party: ");
            String candParty = obj.nextLine();

            System.out.print("Enter Candidate Region: ");
            String candRegion = obj.nextLine();

            String query = "INSERT INTO candidateDatabase (regionCode, candidateName, candidateParty) VALUES(?, ?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, candRegion);
                pstmt.setString(2, candName);
                pstmt.setString(3, candParty);
                pstmt.executeUpdate();
                System.out.println("Added SUCCESSFULLY!\n\n");
                adminRights();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (reply.equals("2") || reply.equalsIgnoreCase("add voter") || reply.contains("voter")) {
            System.out.print("Enter Voter Name: ");
            String voterName = obj.nextLine();

            System.out.print("Enter Voter Aadhar Number: ");
            String voterAadhar = obj.nextLine();

            System.out.print("Enter Voter Mobile No: ");
            String voterMobNo = obj.nextLine();

            System.out.print("Enter Voter Region: ");
            String voterRegion = obj.nextLine();

            String query1 = "INSERT INTO voterDatabase (voterName, aadharNumber, mobNo, regionCode, voteStatus) VALUES(?,?,?,?,?)";

            try (PreparedStatement pstmt = con.prepareStatement(query1)) {
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
        } else if (reply.equals("3") || reply.equalsIgnoreCase("start election") || reply.contains("start")) {
            startElection();
        } else if (reply.equals("4") || reply.equalsIgnoreCase("end election") || reply.contains("end")) {
            endElection();
        } else if (reply.equals("5") || reply.equalsIgnoreCase("exit") || reply.contains("xit")) {
            System.out.println("EXITING. . . . ");
            System.exit(0);
        } else {
            System.exit(0);
        }
    }

    public static void startElection() {
        String query = "UPDATE VotingStatuss SET votingStatus = 'ON' WHERE id = 1";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Election Started!\n\n\n\n");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void endElection() {
        String query = "UPDATE VotingStatuss SET votingStatus = 'END' WHERE id = 1";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Election Ended!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean ElectionStatus() {
        String query = "SELECT votingStatus FROM VotingStatuss WHERE id = 1";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                String status = rs.getString("votingStatus");
                return status.equals("ON");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
