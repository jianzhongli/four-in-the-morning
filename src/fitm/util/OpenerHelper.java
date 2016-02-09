package fitm.util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpenerHelper {
    private DataSource pool; // Database connection mDataSourcePool
    private static OpenerHelper mInsatnce;

    private OpenerHelper() throws ServletException {
        try {
            // Create a JNDI Initial context to be able to lookup the Datasource
            InitialContext context = new InitialContext();
            // Lookup the DataSource, which will be backed by a pool
            pool = (DataSource) context.lookup(Tags.DATA_SOURCE_PREFIX + Tags.DATA_SOURCE);
            if (pool == null) {
                throw new ServletException("Unknown DataSource: "+ Tags.DATA_SOURCE);
            }
        } catch (NamingException ex) {
            Logger.getLogger(OpenerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OpenerHelper getInstance() throws ServletException {
        if (mInsatnce == null) {
            mInsatnce = new OpenerHelper();
        }
        return mInsatnce;
    }

    public boolean validate(String userid, String password) {
        boolean flag = false;
        String[] columns = {Columns.PASSWORD};
        String selection = Columns.USER_ID + "=? ";
        String[] selectionArgs = {userid};
        ResultSet rs = query(TABLE_USER_WEB, columns, selection, selectionArgs, null);
        try {
            while (rs.next()) {
                if (password.equals(rs.getString(Columns.PASSWORD))) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OpenerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    final static String TABLE_USER_WEB = "USER_WEB";
    final static String TABLE_COURSE = "[dbo].[COURSE]";
    final static String TABLE_COURSE_CLASS = "[dbo].[COURSE_CLASS]";
    final static String TABLE_CLASS_STUDENT = "[dbo].[CLASS_STUDENT]";
    private interface Columns {
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
                sb.append("ORDER BY " + orderBy);
            }

            rs = stat.executeQuery(sb.toString());
        } catch (SQLException ex) {
            Logger.getLogger(OpenerHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
