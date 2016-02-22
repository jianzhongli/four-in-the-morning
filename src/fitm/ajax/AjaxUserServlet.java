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
import java.util.HashMap;

public class AjaxUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response;

        String[] pathItems = req.getRequestURI().split("[/]");
        String userid = pathItems[pathItems.length-1];
        User user = User.getUserById(userid);
        if (user != null) {
            response = new Success(user);
        } else {
            response = new Failure("用户不存在");
        }

        writer.print(Utils.getGson().toJson(response));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String userid  = user.getId();
            HashMap<String, String> parameters = new HashMap<>();
            parameters = Utils.getGson().fromJson(Utils.getRequestBody(req), parameters.getClass());
            String old_password = parameters.get(Tags.TAG_OLD_PASSWORD);
            String new_password = parameters.get(Tags.TAG_PASSWORD);
            System.out.println(old_password);
            System.out.println(new_password);
            if (User.validate(userid, old_password) != null) {
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
