package DVote;

import Essentials.*;
import java.sql.*;
import java.util.*;

public class Main
{
    static Scanner obj=new Scanner(System.in);
    static Connection con;
    static{
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/DVoteIndia", "root", "qwertyuiop1234");
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
    public static void main(String[] args) throws SQLException {
        run();
    }

    private static void run() throws SQLException {
        System.out.print("WELCOME TO DVOTE INDIA\nCONTINUE LOGIN: ");
        String reply=obj.nextLine();
        if(ConfirmReply.confirmation(reply))
        {
            boolean statusReport=Admins.ElectionStatus();
            if(statusReport)
            {
                VotersLogin.voterLogin();
            }
            else {
                System.out.println("\n\nELECTIONS HAVE ENDED");
                System.out.println("EXITING. . . . ");
                System.exit(0);
            }
        }
        else if(reply.equals("adminLogin"))
        {
            Admins.adminRights();
        }
    }
}