package fitm.model;

import java.sql.Timestamp;
import java.util.Date;

public class HomeworkPost {
    private Long course_id;
    private String homework_title;
    private String hoemwork_description;
    private String attatch_file;
    private Date  post_date;
    private Timestamp ddl;

    public HomeworkPost(Long course_id, String homework_title, String hoemwork_description, String attatch_file, Date post_date, Timestamp ddl) {
        this.course_id = course_id;
        this.homework_title = homework_title;
        this.hoemwork_description = hoemwork_description;
        this.attatch_file = attatch_file;
        this.post_date = post_date;
        this.ddl = ddl;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public void setHomework_title(String homework_title) {
        this.homework_title = homework_title;
    }

    public String getHoemwork_description() {
        return hoemwork_description;
    }

    public void setHoemwork_description(String hoemwork_description) {
        this.hoemwork_description = hoemwork_description;
    }

    public String getAttatch_file() {
        return attatch_file;
    }

    public void setAttatch_file(String attatch_file) {
        this.attatch_file = attatch_file;
    }

    public Date getPost_date() {
        return post_date;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public Timestamp getDdl() {
        return ddl;
    }

    public void setDdl(Timestamp ddl) {
        this.ddl = ddl;
    }
}
