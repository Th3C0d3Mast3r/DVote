package DVote;
import Essentials.*;
import java.sql.*;
import java.util.*;
public class VotersLogin
{
    static Scanner obj = new Scanner(System.in);
    static Connection con;

    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DVoteIndia", "root", "qwertyuiop1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void voterLogin()
    {
        System.out.print("ENTER VOTER NAME: ");
        String vName=obj.nextLine();

        System.out.print("ENTER AADHAR NUMBER: ");
        String vAadhar=obj.nextLine();

        System.out.print("ENTER MOBILE NUMBER: ");
        String vMobNo=obj.nextLine();

        System.out.print("ENTER REGION CODE: ");
        String vRegion=obj.nextLine();

        String query="SELECT voteStatus FROM voterDatabase WHERE voterName=? AND aadharNumber=? AND mobNo=? AND regionCode=?";

        try(PreparedStatement pstmt=con.prepareStatement(query))
        {
            pstmt.setString(1, vName);
            pstmt.setString(2, vAadhar);
            pstmt.setString(3, vMobNo);
            pstmt.setString(4, vRegion);
            ResultSet rs=pstmt.executeQuery();

            if(rs.next())
            {
                String voteStatus=rs.getString("voteStatus"); //this stores the vote status
                if(voteStatus.equals("NOT VOTED") || voteStatus.contains("NOT"))
                {
                    System.out.println("\nLOGGED IN SUCCESSFULLY "+vName);
                    displayRules();
                    System.out.println("\nPROCEED TO CAST VOTE\n\n");
                    Voting.castVote(vName, vAadhar, vMobNo, vRegion);
                }
                else {
                    System.out.println("YOU HAVE ALREADY VOTED");
                    System.out.println("THANK YOU FOR PARTICIPATING IN ELECTIONS");

                }
            }
            else {
                System.out.println("WRONG CREDENTIALS");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayRules()
    {
        System.out.println("Only Registered Voters Can Cast Their Votes");
        System.out.println("If Your Name is not in the Voter's List, You Cannot Cast Vote");
        System.out.println("Also, Voters must Not Vote Under any External Pressure or Force");
        System.out.println("And Once Voted, the Vote Cannot be Withdrawn-Cast Vote Carefully");
    }
}
