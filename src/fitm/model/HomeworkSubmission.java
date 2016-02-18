package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeworkSubmission {
    String homework_id;
    Student student;
    Timestamp submit_date;
    String attach_file;
    int score;

    public HomeworkSubmission(String homework_id, Student student, Timestamp submit_date, String attach_file, int score) {
        this.homework_id = homework_id;
        this.student = student;
        this.submit_date = submit_date;
        this.attach_file = attach_file;
        this.score = score;
    }

    public String getHomework_id() {
        return homework_id;
    }

    public Student getStudent() {
        return student;
    }

    public Timestamp getSubmit_date() {
        return submit_date;
    }

    public String getAttach_file() {
        return attach_file;
    }

    public int getScore() {
        return score;
    }

    public static HashMap<String, HomeworkSubmission> getSubmissionMapByHomeworkId(String homework_id) throws ServletException {
        HashMap<String, HomeworkSubmission> hashMap = new HashMap<>();

        String selection = SQLHelper.Columns.HOMEWORK_ID + " = ? ";
        String[] selectionArgs = {homework_id};
        ResultSet rs = SQLHelper.getInstance().query(
                SQLHelper.TABLE_HOMEWORK_SUBMISSION, null, selection, selectionArgs, SQLHelper.Columns.SUBMIT_DATE);

        try {
            while (rs.next()) {
                String student_id = rs.getString(SQLHelper.Columns.STUDENT_ID);
                Timestamp submit_date = rs.getTimestamp(SQLHelper.Columns.SUBMIT_DATE);
                String attach_file = rs.getString(SQLHelper.Columns.ATTACH_FILE);
                int score = rs.getInt(SQLHelper.Columns.SCORE);
                hashMap.put(
                        student_id,
                        new HomeworkSubmission(
                                homework_id, new Student(User.getUserById(student_id)), submit_date, attach_file, score));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeworkSubmission.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hashMap;
    }


}

