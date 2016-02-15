package fitm.model;

import fitm.util.SQLHelper;

import javax.servlet.ServletException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mail {
    private String from;
    private String to;
    private String content;
    private boolean has_read;

    public Mail(String from, String to, String content, boolean has_read) {
        this.from = from;
        this.to = to;
        this.content = content;
        this.has_read = has_read;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHasRead() {
        return has_read;
    }

    public void setHasRead(boolean has_read) {
        this.has_read = has_read;
    }

    public static boolean sendMail(Mail mail) throws ServletException {
        boolean flag = false;
        try {
            PreparedStatement pstat = SQLHelper.getInstance().getConnection().prepareStatement(
                    String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                            SQLHelper.TABLE_MAILBOX,
                            SQLHelper.Columns.FROM,
                            SQLHelper.Columns.TO,
                            SQLHelper.Columns.CONTENT,
                            SQLHelper.Columns.HAS_READ
                    )
            );
            pstat.setString(1, mail.getFrom());
            pstat.setString(2, mail.getTo());
            pstat.setString(3, mail.getContent());
            pstat.setBoolean(4, mail.isHasRead());

            if (pstat.executeUpdate() >= 0) {
                flag = true;
            }
            SQLHelper.getInstance().closePreparedStatement(pstat);
        } catch (SQLException ex) {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
}
