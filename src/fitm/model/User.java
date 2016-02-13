package fitm.model;

import fitm.util.SQLHelper;
import fitm.util.Utils;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private String id;
    private String name;
    private int userType;

    public User(String id, String name, int userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUserType() {
        return userType;
    }

    public static User validate(String userid, String password) throws ServletException {
        SQLHelper helper = SQLHelper.getInstance();
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, null, selection, selectionArgs, null);
        User user = null;
        if (rs != null) {
            try {
                if (rs.next()) {
                    if (password.equals(rs.getString(SQLHelper.Columns.PASSWORD))) {
                        user = new User(
                                userid,
                                rs.getString(SQLHelper.Columns.REALNAME),
                                rs.getInt(SQLHelper.Columns.USERTYPE)
                        );
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }
}
