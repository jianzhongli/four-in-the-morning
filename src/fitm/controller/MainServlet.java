package fitm.controller;

import fitm.util.SQLHelper;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    SQLHelper SQLHelper;

    @Override
    public void init() throws ServletException {
        SQLHelper = SQLHelper.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!Utils.hasLogin(req)) {
            req.getRequestDispatcher("/login").forward(req, resp);
        } else {
            req.getRequestDispatcher("/main.jsp").forward(req, resp);
        }
    }
}
