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
import javax.servlet.jsp.tagext.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjaxLoginServlet extends HttpServlet {
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

        String userid = req.getParameter(Tags.TAG_USERID);
        String passwd = req.getParameter(Tags.TAG_PASSWORD);
        User user = User.validate(userid, passwd);

        if (user != null) {
            req.getSession().setAttribute(Tags.TAG_USER, user);
            response = new Success(null);
        } else {
            response = new Failure("用户名或密码错误");
        }
        writer.write(Utils.getGson().toJson(response));
    }
}
