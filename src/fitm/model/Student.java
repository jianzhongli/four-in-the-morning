package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student extends User {
    public Student(String id, String name, int userType) {
        super(id, name, userType);
    }

    public static ArrayList<Student> getStudentsList(String classid) throws ServletException {
        //SELECT * FROM USER_WEB WHERE user_id IN (SELECT student_id FROM CLASS_STUDENT WHERE class_id = '46000071153001';
        ArrayList<Student> studentArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s IN (SELECT %s FROM %s WHERE %s = '%s')",
                SQLHelper.TABLE_USER_WEB, SQLHelper.Columns.USER_ID, SQLHelper.Columns.STUDENT_ID,
                SQLHelper.TABLE_CLASS_STUDENT, SQLHelper.Columns.CLASS_ID, classid);
        ResultSet rs = helper.executeQuery(sql);
        try {
            while(rs.next()) {
                String studentId = rs.getString(SQLHelper.Columns.USER_ID);
                String studentName = rs.getString(SQLHelper.Columns.REALNAME);
                studentArrayList.add(new Student(studentId, studentName, SQLHelper.USERTYPE_STUDENT));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentArrayList;
    }
}
