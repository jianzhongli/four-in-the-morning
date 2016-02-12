package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean validate(String userid, String password) throws ServletException {
        boolean flag = false;
        SQLHelper helper = SQLHelper.getInstance();
        String[] columns = {SQLHelper.Columns.PASSWORD};
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, columns, selection, selectionArgs, null);
        try {
            while (rs.next()) {
                if (password.equals(rs.getString(SQLHelper.Columns.PASSWORD))) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public static String getUserType(String userid) throws ServletException {
        String userType = null;
        SQLHelper helper = SQLHelper.getInstance();
        String[] columns = {SQLHelper.Columns.USERTYPE};
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, columns, selection, selectionArgs, null);
        try {
            while(rs.next()) {
                userType = rs.getString(SQLHelper.Columns.USERTYPE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userType;
    }
}
