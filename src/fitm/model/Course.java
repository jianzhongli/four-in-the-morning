package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Course {
    private String course_id;
    private String course_name;
    private Date course_begin;
    private Date course_end;

    public Course(String course_id, String course_name, Date course_begin, Date course_end) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_begin = course_begin;
        this.course_end = course_end;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Date getCourse_begin() {
        return course_begin;
    }

    public void setCourse_begin(Date course_begin) {
        this.course_begin = course_begin;
    }

    public Date getCourse_end() {
        return course_end;
    }

    public void setCourse_end(Date course_end) {
        this.course_end = course_end;
    }

    public static ArrayList<Course> getCoursesList(String userid) throws ServletException {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();

        String sql = String.format("SELECT * FROM %s WHERE %s IN " +
                "(SELECT %s FROM %s WHERE %s IN " +
                    "(SELECT %s FROM %s WHERE %s = '%s'));",
                SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.CLASS_ID,
                SQLHelper.Columns.CLASS_ID, SQLHelper.TABLE_CLASS_STUDENT, SQLHelper.Columns.STUDENT_ID, userid
        );
        ResultSet rs = helper.executeQuery(sql);
        try {
            while (rs.next()) {
                String courseId = rs.getString(SQLHelper.Columns.COURSE_ID);
                String courseName = rs.getString(SQLHelper.Columns.COURSE_NAME);
                Date courseBegin = rs.getTimestamp(SQLHelper.Columns.COURSE_BEGIN);
                Date courseEnd = rs.getTimestamp(SQLHelper.Columns.COURSE_END);
                courseArrayList.add(new Course(courseId, courseName, courseBegin, courseEnd));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseArrayList;
    }
}
