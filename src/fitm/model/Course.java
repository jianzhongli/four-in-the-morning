package fitm.model;

import fitm.util.SQLHelper;
import fitm.util.Utils;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Course {
    private String course_id;
    private String course_name;
    private Date course_begin;
    private Date course_end;
    private Teacher teacher;
    private ArrayList<Class> classes;

    public Course(String course_id, String course_name, Date course_begin, Date course_end, Teacher teacher, ArrayList<Class> classes) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_begin = course_begin;
        this.course_end = course_end;
        this.teacher = teacher;
        this.classes = classes;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public static ArrayList<Course> getCoursesList(String userid) throws ServletException {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();
        User user = User.getUserById(userid);

        String sql = "";
        if (user.getUserType() == SQLHelper.USERTYPE_STUDENT) {
            sql = String.format("SELECT * FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s = '%s'));",
                    SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                    SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.CLASS_ID,
                    SQLHelper.Columns.CLASS_ID, SQLHelper.TABLE_CLASS_STUDENT, SQLHelper.Columns.STUDENT_ID, userid
            );

        } else if (user.getUserType() == SQLHelper.USERTYPE_TEACHER) {
            sql = String.format("SELECT * FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s = '%s');",
                    SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                    SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.TEACHER_ID, userid
            );
        }
        ResultSet rs = helper.executeQuery(sql);
        if (rs != null) {
            try {
                while (rs.next()) {
                    String courseId = rs.getString(SQLHelper.Columns.COURSE_ID);
                    String courseName = rs.getString(SQLHelper.Columns.COURSE_NAME);
                    Date courseBegin = rs.getTimestamp(SQLHelper.Columns.COURSE_BEGIN);
                    Date courseEnd = rs.getTimestamp(SQLHelper.Columns.COURSE_END);

                    Teacher teacher = null;
                    if(user.getUserType() == SQLHelper.USERTYPE_STUDENT) {
                        teacher = Student.getMyCourseTeacher(courseId, userid);
                    } else if(user.getUserType() == SQLHelper.USERTYPE_TEACHER) {
                        teacher = new Teacher(user.getId(), user.getName(), user.getUserType());
                    }

                    courseArrayList.add(new Course(courseId, courseName, courseBegin, courseEnd, teacher, null));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    helper.closeResultSet(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return courseArrayList;
    }

    public static Course getCourseDetail(String courseid, User user) throws ServletException {
        Course courseDetail = null;
        SQLHelper helper = SQLHelper.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s'",
                SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID, courseid);
        ResultSet rs = helper.executeQuery(sql);

        if (rs != null) {
            try {
                while (rs.next()) {
                    String courseId = rs.getString(SQLHelper.Columns.COURSE_ID);
                    String courseName = rs.getString(SQLHelper.Columns.COURSE_NAME);
                    Date courseBegin = rs.getTimestamp(SQLHelper.Columns.COURSE_BEGIN);
                    Date courseEnd = rs.getTimestamp(SQLHelper.Columns.COURSE_END);

                    switch (user.getUserType()) {
                        case SQLHelper.USERTYPE_STUDENT: {
                            courseDetail = new Course(courseId, courseName, courseBegin, courseEnd, new Teacher("123", "Teacher", 1), null);
                            break;
                        }
                        case SQLHelper.USERTYPE_TEACHER: {
                            ArrayList<Class> classes = Class.getClassesList(courseid, user);
                            courseDetail = new Course(courseId, courseName, courseBegin, courseEnd, new Teacher("123", "Teacher", 1), classes);
                        }
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    helper.closeResultSet(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return courseDetail;
    }
}
