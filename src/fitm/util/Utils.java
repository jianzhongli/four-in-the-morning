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

    // private constructor to prevent instantiation
    private Utils() {}

    public static boolean hasLogin(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute(Tags.TAG_USERID) != null;
    }

    public static User getCurrentUser(HttpServletRequest req) throws ServletException {
        User user = null;
        HttpSession session = req.getSession();
        if (session.getAttribute(Tags.TAG_USERID) != null) {
            String userid  = session.getAttribute(Tags.TAG_USERID).toString();
            SQLHelper helper = SQLHelper.getInstance();
            String selection = SQLHelper.Columns.USER_ID + "=? ";
            String[] selectionArgs = {userid};
            ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, null, selection, selectionArgs, null);
            try {
                while (rs.next()) {
                    user = new User(
                            rs.getLong(SQLHelper.Columns.USER_ID),
                            rs.getString(SQLHelper.Columns.REALNAME));
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
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
