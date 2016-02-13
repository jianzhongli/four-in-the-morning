package fitm.util;

import com.google.gson.Gson;
import fitm.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {
    private static Gson gson;
    private static User currentUser;

    // private constructor to prevent instantiation
    private Utils() {}

    public static boolean hasLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute(Tags.TAG_USERID) != null;
    }

    public static User getCurrentUser(HttpServletRequest req) throws ServletException {
        return (User) req.getSession().getAttribute(Tags.TAG_USER);
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String getSubmittedFileNameExtension(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
                        .substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
                return '.' + fileName.substring(fileName.lastIndexOf('.')+1);
            }
        }
        return null;
    }

    public static Date parseDate(String string) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa", Locale.ENGLISH);
        try {
            Date date = format.parse(string);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.WARNING, null, ex);
        }
        return new Date();
    }
}
