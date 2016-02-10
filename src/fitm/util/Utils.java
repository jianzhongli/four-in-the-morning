package fitm.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    private static Gson gson;

    // private constructor to prevent instantiation
    private Utils() {}

    public static boolean hasLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute(Tags.TAG_USERID) != null;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }
}
