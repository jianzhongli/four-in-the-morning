package fitm.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLHelper {
    private DataSource pool; // Database connection mDataSourcePool
    private static SQLHelper mInstance;

    private SQLHelper() throws ServletException {
        try {
            // Create a JNDI Initial context to be able to lookup the Datasource
            InitialContext context = new InitialContext();
            // Lookup the DataSource, which will be backed by a pool
            pool = (DataSource) context.lookup(Tags.DATA_SOURCE_PREFIX + Tags.DATA_SOURCE);
            if (pool == null) {
                throw new ServletException("Unknown DataSource: "+ Tags.DATA_SOURCE);
            }
        } catch (NamingException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static SQLHelper getInstance() throws ServletException {
        if (mInstance == null) {
            mInstance = new SQLHelper();
        }
        return mInstance;
    }

    final public static String TABLE_USER_WEB = "USER_WEB";
    final public static String TABLE_COURSE = "COURSE";
    final public static String TABLE_COURSE_CLASS = "COURSE_CLASS";
    final public static String TABLE_CLASS_STUDENT = "CLASS_STUDENT";
    final public static String TABLE_HOMEWORK_POST = "HOMEWORK_POST";
    final public static String TABLE_HOMEWORK_SUBMISSION = "HOMEWORK_SUBMISSION";
    final public static String TABLE_MAILBOX = "MAILBOX";
    final public static String TABLE_CLASS_TA = "CLASS_TA";

    public interface Columns {
        final static String USER_ID                 = "user_id";
        final static String PASSWORD                = "password";
        final static String REALNAME                = "realname";
        final static String USERTYPE                = "user_type";
        final static String COURSE_ID               = "course_id";
        final static String COURSE_NAME             = "course_name";
        final static String COURSE_BEGIN            = "course_begin";
        final static String COURSE_END              = "course_end";
        final static String CLASS_ID                = "class_id";
        final static String CLASS_NAME              = "class_name";
        final static String TEACHER_ID              = "teacher_id";
        final static String STUDENT_ID              = "student_id";
        final static String TA                      = "ta";
        final static String HOMEWORK_ID             = "homework_id";
        final static String HOMEWORK_TITLE          = "homework_title";
        final static String HOMEWORK_DESCRIPTION    = "homework_description";
        final static String ATTACH_FILE             = "attach_file";
        final static String POST_DATE               = "post_date";
        final static String DDL                     = "ddl";
        final static String SUBMIT_DATE             = "submit_date";
        final static String SCORE                   = "score";
        final static String MAIL_FROM               = "mail_from";
        final static String MAIL_TO                 = "mail_to";
        final static String CONTENT                 = "content";
        final static String MAIL_DATE               = "mail_date";
        final static String HAS_READ                = "has_read";
    }

    /**
     * 仿 Android SQLiteDatabase 设计的 query 方法。
     * @param table 表名，举例："Books".
     * @param columns 返回的 Cursor/ResultSet 中包含的列，举例：{"_id", "name"}. 此参数为 null 或空数组时会返回全部列。
     * @param selection 选择语句。举例：" _id = ? AND name = ?"，问号为占位符。
     * @param selectionArgs 选择语句的参数，即会被依次替换到选择语句中。举例：{"123", "红与黑"}.
     * @param orderBy 排序依据，指定返回结果集按某列排序。举例："_id".
     * @return 结果集，相当于 Cursor. 详见 Java 文档。上面的例子结合起来，相当于执行了如下 SQL 语句：
     *      SELECT _id, name FROM Books WHERE _id='123' AND name='红与黑' ORDER BY _id;
     */
    public ResultSet query(
            String table, String[] columns, String selection, String[] selectionArgs, String orderBy) {
        ResultSet rs = null;
        Connection conn;
        Statement stat;
        try {
            // Get a connection from the pool
            conn = pool.getConnection();
            stat = conn.createStatement();
            StringBuilder sb = new StringBuilder("SELECT ");
            if (columns != null && columns.length > 0) {
                boolean flag = true;
                for (String c : columns) {
                    if (flag) {
                        flag = false;
                    } else {
                        sb.append(", ");
                    }
                    sb.append(c);
                }
                sb.append(" ");
            } else { // 当 columns 为空或长度为 0 时选择所有列
                sb.append("* ");
            }

            // add table name
            sb.append("FROM " + table + " ");

            // process the where clause
            sb.append("WHERE ");
            String[] tokens = selection.split("[?]");
            for (int i = 0; i < selectionArgs.length; ++i) {
                sb.append(tokens[i] + selectionArgs[i]);
            }

            if (orderBy != null) {
                // process the ORDER BY part
                sb.append(" ORDER BY " + orderBy);
            }

            System.out.println(sb.toString());
            rs = stat.executeQuery(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet executeQuery(String sql) {
        System.out.println(sql);

        ResultSet rs = null;
        Connection conn;
        Statement stat;
        try {
            conn = pool.getConnection();
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int executeUpdate(String sql) {
        System.out.println(sql);

        Connection conn = null;
        Statement stat = null;
        int ret = -1;
        try {
            conn = pool.getConnection();
            stat = conn.createStatement();
            ret = stat.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stat != null) {
                    stat.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ret;
    }

    public void closeResultSet(ResultSet rs) throws SQLException {
        Statement stat = rs.getStatement();
        Connection conn = stat.getConnection();
        rs.close();
        stat.close();
        conn.close();
    }

    public void closePreparedStatement(PreparedStatement pstat) throws SQLException {
        Connection conn = pstat.getConnection();
        pstat.close();
        conn.close();
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
