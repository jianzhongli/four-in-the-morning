package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeworkPost {
    private String course_id;
    private String homework_id;
    private String homework_title;
    private String homework_description;
    private String attach_file;
    private Timestamp post_date;
    private Timestamp ddl;

    public HomeworkPost(String course_id, String homework_id, String homework_title, String homework_description, String attach_file, Timestamp post_date, Timestamp ddl) {
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

    public Timestamp getPost_date() {
        return post_date;
    }

    public Timestamp getDdl() {
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
            pstmt.setTimestamp(6, homeworkPost.getPost_date());
            pstmt.setTimestamp(7, homeworkPost.getDdl());

            if (pstmt.executeUpdate() >= 0) {
                flag = true;
            }
            SQLHelper.getInstance().closePreparedStatement(pstmt);
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public static HomeworkPost getHomeworkPostById(String homework_id) throws ServletException {
        HomeworkPost homeworkPost = null;

        String selection = SQLHelper.Columns.HOMEWORK_ID + " = ?";
        String[] selectionArgs = {homework_id};
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
                    homeworkPost = new HomeworkPost(
                            rs.getString(SQLHelper.Columns.COURSE_ID),
                            rs.getString(SQLHelper.Columns.HOMEWORK_ID),
                            rs.getString(SQLHelper.Columns.HOMEWORK_TITLE),
                            rs.getString(SQLHelper.Columns.HOMEWORK_DESCRIPTION),
                            rs.getString(SQLHelper.Columns.ATTACH_FILE),
                            rs.getTimestamp(SQLHelper.Columns.POST_DATE),
                            rs.getTimestamp(SQLHelper.Columns.DDL)
                    );
                }
                SQLHelper.getInstance().closeResultSet(rs);
            } catch (SQLException ex) {
                Logger.getLogger(HomeworkPost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return homeworkPost;
    }

    public static ArrayList<HomeworkPost> getHomeworkPostByCourseId(String course_id) throws ServletException{
        ArrayList<HomeworkPost> homeworkPostArrayList = new ArrayList<>();

        String selection = SQLHelper.Columns.COURSE_ID + " = ?";
        String[] selectionArgs = {course_id};
        ResultSet rs = SQLHelper.getInstance().query(
                SQLHelper.TABLE_HOMEWORK_POST,
                null,
                selection,
                selectionArgs,
                SQLHelper.Columns.POST_DATE + " DESC" // 时间倒序，最晚的在最前
                // TODO: this is a hack, should switch to something better
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
                            rs.getTimestamp(SQLHelper.Columns.POST_DATE),
                            rs.getTimestamp(SQLHelper.Columns.DDL)
                    ));
                }
                SQLHelper.getInstance().closeResultSet(rs);
            } catch (SQLException ex) {
                Logger.getLogger(HomeworkPost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return homeworkPostArrayList;
    }

    public static boolean deleteHomeworkPost(String homework_id) throws ServletException {
        String sql = String.format("DELETE FROM %s WHERE %s = %s",
                SQLHelper.TABLE_HOMEWORK_POST,
                SQLHelper.Columns.HOMEWORK_ID,
                homework_id
        );
        return SQLHelper.getInstance().executeUpdate(sql) > 0;
    }
}
