package fitm.ajax;

import fitm.model.User;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxUpdateUserServlet extends HttpServlet {
    SQLHelper helper;

    @Override
    public void init() throws ServletException {
        helper = SQLHelper.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response;

        if (Utils.hasLogin(req)) {
            HttpSession session = req.getSession();
            String userid  = session.getAttribute(Tags.TAG_USERID).toString();
            String old_password = req.getParameter(Tags.TAG_OLD_PASSWORD);
            String new_password = req.getParameter(Tags.TAG_PASSWORD);
            if (User.validate(userid, old_password)) {
                if (SQLHelper.getInstance().executeUpdate(String.format(
                        "UPDATE %s SET %s='%s' WHERE %s='%s'",
                        SQLHelper.TABLE_USER_WEB, SQLHelper.Columns.PASSWORD, new_password,
                        SQLHelper.Columns.USER_ID, userid
                )) >= 0) {
                    response = new Success(null);
                } else {
                    response = new Failure("网络错误。");
                }
            } else {
                response = new Failure("密码错误。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }
}
