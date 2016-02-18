package fitm.controller;

import fitm.model.User;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    SQLHelper helper;

    @Override
    public void init() throws ServletException {
        helper = SQLHelper.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getCurrentUser(req);
        if (user != null) {
            req.getRequestDispatcher(Path.OVERVIEW).forward(req, resp);
        } else {
            req.getRequestDispatcher(Path.PROMOTION).forward(req, resp);
        }
    }
}
