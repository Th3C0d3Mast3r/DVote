package Essentials;

import java.util.regex.*;
import java.util.HashSet;
import java.util.Set;

public class EmailChecker {
    private static final Set<String> VALID_DOMAINS = new HashSet<>();

    static {
        String[] domains = {
                "gmail.com", "yahoo.com", "outlook.com", "rediffmail.com",
                "icloud.com", "mailchimp.com", "zoho.com", "gmx.com",
                "tutanota.com", "aol.com", "yandex.com", "protonmail.com",
                "fastmail.com", "hey.com", "mail.com", "hushmail.com",
                "mailfence.com", "runbox.com", "zohomail.com", "mail.ru"
        };
        for (String domain : domains) {
            VALID_DOMAINS.add(domain);
        }
    }

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
    );

    public static boolean checkEmail(String emailId) {
        if (emailId == null || emailId.isEmpty()) {
            return false;
        }

        emailId = emailId.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(emailId).matches()) {
            return false;
        }

        String domain = emailId.substring(emailId.lastIndexOf('@') + 1);
        return VALID_DOMAINS.contains(domain);
    }
}
