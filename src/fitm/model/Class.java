package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Class {
    private String class_id;
    private String class_name;
    private Teacher teacher;
    private ArrayList<Student> ta_list;
    private ArrayList<Student> students;

    public Class(String class_id, String class_name, Teacher teacher, ArrayList<Student> students) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.teacher = teacher;
        this.students = students;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Student> getTa_list() {
        return ta_list;
    }

    public void setTa_list(ArrayList<Student> ta_list) {
        this.ta_list = ta_list;
    }

    public static ArrayList<Class> getClassesList(String courseid, User user) throws ServletException {
        ArrayList<Class> classeArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.COURSE_ID, courseid, SQLHelper.Columns.TEACHER_ID, user.getId());
        ResultSet rs = helper.executeQuery(sql);
        try {
            while(rs.next()) {
                String classId = rs.getString(SQLHelper.Columns.CLASS_ID);
                String className = rs.getString(SQLHelper.Columns.CLASS_NAME);
                Teacher teacher = new Teacher(user);
                ArrayList<Student> studentsArrayList = Student.getStudentsList(classId);
                Class teaching_class = new Class(classId, className, teacher, studentsArrayList);

                String sql2 = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                        SQLHelper.Columns.TA, SQLHelper.TABLE_CLASS_TA, SQLHelper.Columns.CLASS_ID, classId);
                ResultSet rs2 = helper.executeQuery(sql2);
                ArrayList<Student> ta_list = new ArrayList<>();
                try {
                    while (rs2 != null && rs2.next()) {
                        String ta_id = rs2.getString(SQLHelper.Columns.TA);
                        ta_list.add(new Student(User.getUserById(ta_id)));
                    }
                } catch (SQLException ex) {
                    throw ex;
                }
                teaching_class.setTa_list(ta_list);
                classeArrayList.add(teaching_class);
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
        return classeArrayList;
    }

    public static ArrayList<Class> getAssistantClassesList(String courseid, User user) throws ServletException {
        // SELECT * FROM COURSE_CLASS WHERE course_id = ? AND class_id IN (SELECT class_id FROM CLASS_TA WHERE ta = ?)
        ArrayList<Class> classArrayList = new ArrayList<>();
        String sql = String.format(
                "SELECT * FROM %s WHERE %s = %s AND %s IN (SELECT %s FROM %s WHERE %s = %s)",
                SQLHelper.TABLE_COURSE_CLASS,
                SQLHelper.Columns.COURSE_ID, courseid, SQLHelper.Columns.CLASS_ID,
                SQLHelper.Columns.CLASS_ID,
                SQLHelper.TABLE_CLASS_TA,
                SQLHelper.Columns.TA, user.getId()
        );
        ResultSet rs= SQLHelper.getInstance().executeQuery(sql);
        try {
            while(rs.next()) {
                String classId = rs.getString(SQLHelper.Columns.CLASS_ID);
                String className = rs.getString(SQLHelper.Columns.CLASS_NAME);
                String teacherId = rs.getString(SQLHelper.Columns.TEACHER_ID);
                Teacher teacher = new Teacher(User.getUserById(teacherId));
                ArrayList<Student> studentsArrayList = Student.getStudentsList(classId);
                classArrayList.add(new Class(classId, className, teacher, studentsArrayList));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                SQLHelper.getInstance().closeResultSet(rs);
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return classArrayList;
    }

    public static boolean assignAssistantToClasses(String ta_id, ArrayList<String> class_list) throws ServletException {
        boolean flag = false;
        for (String class_id : class_list) {
            String sql = String.format("INSERT INTO %s (%s, %s) VALUES (%s, %s)",
                    SQLHelper.TABLE_CLASS_TA,
                    SQLHelper.Columns.CLASS_ID, SQLHelper.Columns.TA,
                    class_id, ta_id
            );
            flag = SQLHelper.getInstance().executeUpdate(sql) >= 0;
        }
        return flag;
    }
}
