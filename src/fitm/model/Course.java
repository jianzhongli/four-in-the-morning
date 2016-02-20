package fitm.model;

import fitm.util.SQLHelper;
import sun.rmi.runtime.Log;

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

    public static ArrayList<Course> getCoursesList(User user) throws ServletException {
        final long startTime = System.currentTimeMillis(); // for measuring performance

        ArrayList<Course> courseArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();

        String sql = "";
        if (user.isStudent()) {
            sql = String.format("SELECT * FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s = '%s'));",
                    SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                    SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.CLASS_ID,
                    SQLHelper.Columns.CLASS_ID, SQLHelper.TABLE_CLASS_STUDENT, SQLHelper.Columns.STUDENT_ID, user.getId()
            );
        } else if (user.isTeacher()) {
            sql = String.format("SELECT * FROM %s WHERE %s IN " +
                            "(SELECT %s FROM %s WHERE %s = '%s');",
                    SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                    SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.TEACHER_ID, user.getId()
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
                    if(user.isStudent()) {
                        teacher = Student.getMyCourseTeacher(courseId, user.getId());
                    } else if(user.isTeacher()) {
                        teacher = new Teacher(user);
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

        final long endTime = System.currentTimeMillis();
        System.out.println("getCoursesList()" + (endTime-startTime));

        return courseArrayList;
    }

    public static Course getCourseDetail(String courseid, User user) throws ServletException {
        final long startTime = System.currentTimeMillis(); // for measuring performance

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

                    if (user.isTeacher()) {
                        ArrayList<Class> classes = Class.getClassesList(courseid, user);
                        courseDetail = new Course(courseId, courseName, courseBegin, courseEnd, new Teacher(user), classes);
                    } else if (user.isAssistantOfCourse(courseId)) {
                        ArrayList<Class> classes = Class.getAssistantClassesList(courseid, user);
                        courseDetail = new Course(courseId, courseName, courseBegin, courseEnd, null, classes);
                    } else {
                        courseDetail = new Course(courseId, courseName, courseBegin, courseEnd,
                                Student.getMyCourseTeacher(courseId, user.getId()), null);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    helper.closeResultSet(rs);
                } catch (SQLException ex) {
                    Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("getCourseDetail()" + (endTime-startTime));

        return courseDetail;
    }

    // 获取该同学担任助教的课程列表
    public static ArrayList<Course> getAssistantCoursesList(User user) throws ServletException {
        final long startTime = System.currentTimeMillis(); // for measuring performance

        // SELECT * FROM COURSE WHERE course_id IN
        //      (SELECT DISTINCT course_id FROM COURSE_CLASS WHERE class_id IN
        //          (SELECT class_id FROM CLASS_TA WHERE ta = <ta_id>));
        ArrayList<Course> courseArrayList = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s WHERE %s IN " +
                "(SELECT DISTINCT %s FROM %s WHERE %s IN " +
                "(SELECT %s FROM %s WHERE %s = %s));",
                SQLHelper.TABLE_COURSE, SQLHelper.Columns.COURSE_ID,
                SQLHelper.Columns.COURSE_ID, SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.CLASS_ID,
                SQLHelper.Columns.CLASS_ID, SQLHelper.TABLE_CLASS_TA, SQLHelper.Columns.TA, user.getId()
                );
        try {
            ResultSet rs = SQLHelper.getInstance().executeQuery(sql);
            while (rs.next()) {
                String courseId = rs.getString(SQLHelper.Columns.COURSE_ID);
                String courseName = rs.getString(SQLHelper.Columns.COURSE_NAME);
                Date courseBegin = rs.getTimestamp(SQLHelper.Columns.COURSE_BEGIN);
                Date courseEnd = rs.getTimestamp(SQLHelper.Columns.COURSE_END);
                ArrayList<Class> classes = Class.getAssistantClassesList(courseId, user);
                Teacher teacher = Student.getMyCourseTeacher(courseId, user.getId());
                courseArrayList.add(new Course(courseId, courseName, courseBegin, courseEnd, teacher, classes));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }

        final long endTime = System.currentTimeMillis();
        System.out.println("getAssistantCoursesList()" + (endTime-startTime));

        return courseArrayList;
    }

    public static String getCourseIdFromHomeworkId(String homework_id) throws ServletException {
        final long startTime = System.currentTimeMillis(); // for measuring performance

        String course_id = null;
        String[] columns = {SQLHelper.Columns.COURSE_ID};
        String selection = SQLHelper.Columns.HOMEWORK_ID + " = ? ";
        String[] selectioArgs = {homework_id};
        ResultSet rs = SQLHelper.getInstance().query(
                SQLHelper.TABLE_HOMEWORK_POST,
                columns,
                selection,
                selectioArgs,
                null
        );

        if (rs != null) {
            try {
                while (rs.next()) {
                    course_id = rs.getString(SQLHelper.Columns.COURSE_ID);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Course.class.getName()).log(Level.WARNING, null, ex);
            }
        }

        final Long endTime = System.currentTimeMillis();
        System.out.println("getCourseIdFromHomeworkId()" + (endTime-startTime));

        return course_id;
    }
}
