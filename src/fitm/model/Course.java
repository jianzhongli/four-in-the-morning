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
    private Long course_id;
    private String course_name;
    private Date course_begin;
    private Date course_end;
    ArrayList<Class> classes;

    public Course(Long course_id, String course_name, Date course_begin, Date course_end, ArrayList<Class> classes) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_begin = course_begin;
        this.course_end = course_end;
        this.classes = classes;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
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

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }


    public static ArrayList<Course> getCoursesList(String userid) throws ServletException {
        ArrayList<Course> courseArrayList = new ArrayList<>();
        SQLHelper helper = SQLHelper.getInstance();



        return courseArrayList;
    }
}
