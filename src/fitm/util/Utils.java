package fitm.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {

    // private constructor to prevent instantiation
    private Utils() {}

    public static boolean hasLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute(Tags.TAG_USERID) != null;
    }
}
