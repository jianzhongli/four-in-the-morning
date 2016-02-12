package fitm.model;

import fitm.util.SQLHelper;
import fitm.util.Utils;

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
        String selection = SQLHelper.Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = helper.query(SQLHelper.TABLE_USER_WEB, null, selection, selectionArgs, null);
        if (rs != null) {
            try {
                while (rs.next()) {
                    if (password.equals(rs.getString(SQLHelper.Columns.PASSWORD))) {
                        flag = true;
                        Utils.setCurrentUser(new User(new Long(userid), rs.getString(SQLHelper.Columns.REALNAME)));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return flag;
    }
}
