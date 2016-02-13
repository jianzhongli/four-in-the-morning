package fitm.model;

import fitm.util.SQLHelper;
import fitm.util.Utils;

import javax.servlet.ServletException;
import java.sql.Timestamp;
import java.util.Date;

public class HomeworkPost {
    private String course_id;
    private String homework_id;
    private String homework_title;
    private String hoemwork_description;
    private String attatch_file;
    private Date post_date;
    private Date ddl;

    public HomeworkPost(String course_id, String homework_id, String homework_title, String hoemwork_description, String attatch_file, Date post_date, Date ddl) {
        this.course_id = course_id;
        this.homework_id = homework_id;
        this.homework_title = homework_title;
        this.hoemwork_description = hoemwork_description;
        this.attatch_file = attatch_file;
        this.post_date = post_date;
        this.ddl = ddl;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getHomework_id() {
        return homework_id;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public String getHoemwork_description() {
        return hoemwork_description;
    }

    public String getAttatch_file() {
        return attatch_file;
    }

    public Date getPost_date() {
        return post_date;
    }

    public Date getDdl() {
        return ddl;
    }

    public static boolean insertHomeworkPost(HomeworkPost homeworkPost) throws ServletException {
        boolean flag = false;
        String sql = String.format(
                "INSERT INTO %s VALUES (%s %s %s %s %s %s %s)",
                SQLHelper.TABLE_HOMEWORK_POST,
                homeworkPost.getCourse_id(),
                homeworkPost.getHomework_id(),
                homeworkPost.getHomework_title(),
                homeworkPost.getHoemwork_description(),
                homeworkPost.getAttatch_file(),
                homeworkPost.getPost_date(),
                homeworkPost.getDdl()
        );
        SQLHelper helper = SQLHelper.getInstance();
        if (helper.executeUpdate(sql) >= 0) {
            flag = true;
        }

        return flag;
    }
}
