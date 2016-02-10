package fitm.model;

import java.util.ArrayList;

public class Class {
    private Long class_id;
    private String class_name;
    private Teacher teacher;
    private ArrayList<Student> students;

    public Class(Long class_id, Teacher teacher, ArrayList<Student> students) {
        this.class_id = class_id;
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
}
