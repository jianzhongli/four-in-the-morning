package fitm.model;

import fitm.util.SQLHelper;
import fitm.util.Utils;

import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
    final public static int USERTYPE_ADMINISTRATOR = 0;
    final public static int USERTYPE_TEACHER = 1;
    final public static int USERTYPE_STUDENT = 2;

    private String id;
    private String name;
    private int user_type;

    public User(String id, String name, int user_type) {
        this.id = id;
        this.name = name;
        this.user_type = user_type;
    }

    public User(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.user_type = user.getUser_type();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getUser_type() {
        return user_type;
    }

    public boolean isAdmin() {
        return USERTYPE_ADMINISTRATOR == this.user_type;
    }

    public boolean isTeacher() {
        return USERTYPE_TEACHER == this.user_type;
    }

    public boolean isStudent() {
        return USERTYPE_STUDENT == this.user_type;
    }

    public boolean isAssistant() throws ServletException {
        boolean flag = false;
        String sql = String.format("SELECT 1 FROM %s WHERE %s = %s",
                            SQLHelper.TABLE_CLASS_TA, SQLHelper.Columns.TA, this.getId());
        try {
            flag = SQLHelper.getInstance().executeQuery(sql).next();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean isAssistantOfCourse(String course_id) throws ServletException {
        // SELECT ta FROM CLASS_TA WHERE class_id IN (SELECT class_id FROM COURSE_CLASS WHERE course_id = <course_id>);
        boolean flag = false;
        String sql = String.format("SELECT %s FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s = %s)",
                SQLHelper.Columns.TA,
                SQLHelper.TABLE_CLASS_TA,
                SQLHelper.Columns.CLASS_ID,
                SQLHelper.Columns.CLASS_ID,
                SQLHelper.TABLE_COURSE_CLASS,
                SQLHelper.Columns.COURSE_ID,
                course_id);

        try {
            ResultSet rs = SQLHelper.getInstance().executeQuery(sql);
            while (rs.next()) {
                if (this.getId().equals(rs.getString(SQLHelper.Columns.TA))) {
                    flag = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
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
                        switch (user.getUser_type()) {
                            case User.USERTYPE_ADMINISTRATOR: user = new Admin(user); break;
                            case User.USERTYPE_TEACHER: user = new Teacher(user); break;
                            case User.USERTYPE_STUDENT: user = new Student(user); break;
                        }
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
        return user;
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
