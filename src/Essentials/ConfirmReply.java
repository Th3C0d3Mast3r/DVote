package Essentials;

public class ConfirmReply
{
    public static boolean confirmation(String st)
    {
        st=st.trim(); //to remove all the previously present whitespaces

        if(st.equalsIgnoreCase("yes") || st.equalsIgnoreCase("certainly") || st.equalsIgnoreCase("absolutely") || st.equalsIgnoreCase("definitely") || st.equalsIgnoreCase("sure") || st.equalsIgnoreCase("affirmative") || st.equalsIgnoreCase("indeed") || st.equalsIgnoreCase("yep") ||st.equalsIgnoreCase("yeah") || st.equalsIgnoreCase("yea") || st.equalsIgnoreCase("aye") || st.equalsIgnoreCase("agreed"))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
