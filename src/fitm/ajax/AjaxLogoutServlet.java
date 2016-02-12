package fitm.ajax;

import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response = new Success(null);
        try {
            req.getSession().invalidate();
            Utils.setCurrentUser(null);
        } catch (IllegalStateException ex) { // when this session is already invalidated
            response = new Failure("Session 已过期。");
        } finally {
            writer.write(Utils.getGson().toJson(response));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
