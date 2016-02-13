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

    public static boolean validate(String userid, String password) throws ServletException {
        boolean flag = false;
        SQLHelper helper = SQLHelper.getInstance();
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, null, selection, selectionArgs, null);
        if (rs != null) {
            try {
                while (rs.next()) {
                    if (password.equals(rs.getString(SQLHelper.Columns.PASSWORD))) {
                        flag = true;
                        Utils.setCurrentUser(new User(
                                userid,
                                rs.getString(SQLHelper.Columns.REALNAME),
                                rs.getInt(SQLHelper.Columns.USERTYPE)
                        ));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    helper.closeResultSet(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return flag;
    }

    public static User getUserById(String userid) throws ServletException {
        SQLHelper helper = SQLHelper.getInstance();
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        User user = null;

        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, null, selection, selectionArgs, null);
        if (rs != null) {
            try {
                while (rs.next()) {
                    user = new User(userid,
                            rs.getString(SQLHelper.Columns.REALNAME),
                            rs.getInt(SQLHelper.Columns.USERTYPE));
                }
            } catch(SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    helper.closeResultSet(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return user;
    }
}
