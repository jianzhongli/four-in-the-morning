package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeworkPost {
    private String course_id;
    private String homework_id;
    private String homework_title;
    private String homework_description;
    private String attach_file;
    private Date post_date;
    private Date ddl;

    public HomeworkPost(String course_id, String homework_id, String homework_title, String homework_description, String attach_file, Date post_date, Date ddl) {
        this.course_id = course_id;
        this.homework_id = homework_id;
        this.homework_title = homework_title;
        this.homework_description = homework_description;
        this.attach_file = attach_file;
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

    public String getHomework_description() {
        return homework_description;
    }

    public String getAttach_file() {
        return attach_file;
    }

    public Date getPost_date() {
        return post_date;
    }

    public Date getDdl() {
        return ddl;
    }

    public static boolean insertHomeworkPost(HomeworkPost homeworkPost) throws ServletException {
        boolean flag = false;
        try {
            PreparedStatement pstmt = SQLHelper.getInstance().getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
                            SQLHelper.TABLE_HOMEWORK_POST,
                            SQLHelper.Columns.COURSE_ID,
                            SQLHelper.Columns.HOMEWORK_ID,
                            SQLHelper.Columns.HOMEWORK_TITLE,
                            SQLHelper.Columns.HOMEWORK_DESCRIPTION,
                            SQLHelper.Columns.ATTACH_FILE,
                            SQLHelper.Columns.POST_DATE,
                            SQLHelper.Columns.DDL
                    ));
            pstmt.setString(1, homeworkPost.getCourse_id());
            pstmt.setString(2, homeworkPost.getHomework_id());
            pstmt.setString(3, homeworkPost.getHomework_title());
            pstmt.setString(4, homeworkPost.getHomework_description());
            pstmt.setString(5, homeworkPost.getAttach_file());
            pstmt.setDate(6, homeworkPost.getPost_date());
            pstmt.setDate(7, homeworkPost.getDdl());

            if (pstmt.executeUpdate() >= 0) {
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public static ArrayList<HomeworkPost> getHomeworkPost(String course_id) throws ServletException{
        ArrayList<HomeworkPost> homeworkPostArrayList = new ArrayList<>();

        String selection = SQLHelper.Columns.COURSE_ID + " = ?";
        String[] selectionArgs = {course_id};
        ResultSet rs = SQLHelper.getInstance().query(
                SQLHelper.TABLE_HOMEWORK_POST,
                null,
                selection,
                selectionArgs,
                null
        );
        if (rs != null) {
            try {
                while (rs.next()) {
                    homeworkPostArrayList.add(new HomeworkPost(
                            course_id,
                            rs.getString(SQLHelper.Columns.HOMEWORK_ID),
                            rs.getString(SQLHelper.Columns.HOMEWORK_TITLE),
                            rs.getString(SQLHelper.Columns.HOMEWORK_DESCRIPTION),
                            rs.getString(SQLHelper.Columns.ATTACH_FILE),
                            rs.getDate(SQLHelper.Columns.POST_DATE),
                            rs.getDate(SQLHelper.Columns.DDL)
                    ));
                }
                SQLHelper.getInstance().closeResultSet(rs);
            } catch (SQLException ex) {
                Logger.getLogger(HomeworkPost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return homeworkPostArrayList;
    }
}
