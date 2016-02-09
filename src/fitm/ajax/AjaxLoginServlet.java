package fitm.ajax;

import com.google.gson.Gson;
import fitm.util.OpenerHelper;
import fitm.util.Tags;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxLoginServlet extends HttpServlet {
    OpenerHelper openerHelper;

    @Override
    public void init() throws ServletException {
        openerHelper = OpenerHelper.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.write(req.getRequestURI());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Gson gson = new Gson();
        Response response;

        String userid = req.getParameter(Tags.TAG_USERID);
        String passwd = req.getParameter(Tags.TAG_PASSWORD);

        if (openerHelper.validate(userid, passwd)) {
            HttpSession session = req.getSession();
            session.setAttribute(Tags.TAG_USERID, userid);
            response = new Success(null);

        } else {
            response = new Failure("用户名或密码错误");
        }
        writer.write(gson.toJson(response));
    }
}
