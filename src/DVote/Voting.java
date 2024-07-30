package DVote;
import Essentials.*;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
public class Voting
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

    public static void castVote(String vName, String vAadhar, String vMobNo, String vRegion)
    {
        displayCandidates(vRegion);
        System.out.print("CHOICE (PARTY NAME): ");
        String voterChoice=obj.nextLine();

        LocalDate vDate=LocalDate.now(); //fetch the date and time as soon as vote is cast
        LocalTime vTime=LocalTime.now();

        try {
            con.setAutoCommit(false); // Begin transaction

            // Update the voter's status and timestamp
            String updateVoterQuery = "UPDATE voterDatabase SET voteStatus = 'VOTED', voteDate = ?, voteTime = ? WHERE aadharNumber = ?";
            try (PreparedStatement pstmt = con.prepareStatement(updateVoterQuery)) {
                pstmt.setDate(1, Date.valueOf(vDate));
                pstmt.setTime(2, Time.valueOf(vTime));
                pstmt.setString(3, vAadhar);
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated == 0) {
                    System.out.println("Error: Voter information could not be updated.");
                    con.rollback(); // Rollback transaction
                    return;
                }
            }
            // Update the votingDatabase with the chosen party
            String updateVotesQuery = "UPDATE votingDatabase SET totalVotes = totalVotes + 1 WHERE regionCode = ? AND partyName = ?";
            try (PreparedStatement pstmt = con.prepareStatement(updateVotesQuery)) {
                pstmt.setString(1, vRegion);
                pstmt.setString(2, voterChoice);
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated == 0) {
                    // If the party does not exist in the region, insert a new record
                    String insertVotesQuery = "INSERT INTO votingDatabase (regionCode, partyName, totalVotes) VALUES (?, ?, 1)";
                    try (PreparedStatement insertStmt = con.prepareStatement(insertVotesQuery)) {
                        insertStmt.setString(1, vRegion);
                        insertStmt.setString(2, voterChoice);
                        insertStmt.executeUpdate();
                    }
                }
            }
            con.commit(); // Commit transaction
            System.out.println("VOTE CASTED SUCCESSFULLY!");
        }

        catch (SQLException e) {
            try {
                con.rollback(); // Rollback transaction in case of error
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            try {
                con.setAutoCommit(true); // Restore auto-commit mode
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void displayCandidates(String vRegion) {
        String query = "SELECT candidateName, candidateParty FROM candidateDatabase WHERE regionCode=?";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setString(1, vRegion);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\nCANDIDATES FROM YOUR REGION: ");
            while (rs.next()) {
                String name = rs.getString("candidateName");
                String party = rs.getString("candidateParty");
                System.out.println(party + " (" + name + ")");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
