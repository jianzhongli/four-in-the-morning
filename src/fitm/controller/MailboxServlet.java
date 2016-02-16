package fitm.controller;

import fitm.model.Mail;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MailboxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = Utils.getCurrentUser(req);
        if (user != null) {
            //req.setAttribute(Tags.TAG_MAIL_LIST, Mail.getMailsList(user.getId(), Mail.defaultPageSize, Mail.defaultPageIndex));
            req.setAttribute(Tags.TAG_MAIL_LIST_SIZE, Mail.getMailsListMaxSize(user.getId()));
            req.getRequestDispatcher(Path.MAILBOX).forward(req, resp);
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }
}
