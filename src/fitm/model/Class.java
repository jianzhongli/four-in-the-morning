package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Class {
    private Long class_id;
    private String class_name;
    private Teacher teacher;
    private ArrayList<Student> students;

    public Class(Long class_id, String class_name, Teacher teacher, ArrayList<Student> students) {
        this.class_id = class_id;
        this.class_name = class_name;
        this.teacher = teacher;
        this.students = students;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public static ArrayList<Class> getClassesList(String courseid, String userid) throws ServletException {
        ArrayList<Class> classeArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();
        String sql = String.format("SELECT * FROM %s WHERE %s = '%s' AND %s = '%s'",
                SQLHelper.TABLE_COURSE_CLASS, SQLHelper.Columns.COURSE_ID, courseid, SQLHelper.Columns.TEACHER_ID, userid);
        ResultSet rs = helper.executeQuery(sql);
        try {
            while(rs.next()) {
                Long classId = rs.getLong(SQLHelper.Columns.CLASS_ID);
                String className = rs.getString(SQLHelper.Columns.CLASS_NAME);
                //TO DO: Teacher
                Teacher teacher = new Teacher(classId, "HAHA_TEACHER");
                ArrayList<Student> studentsArrayList = Student.getStudentsList(classId);
                classeArrayList.add(new Class(classId, className, teacher, studentsArrayList));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Course.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classeArrayList;
    }
}
