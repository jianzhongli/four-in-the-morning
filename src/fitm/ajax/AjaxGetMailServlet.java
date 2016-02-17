package fitm.ajax;


import fitm.model.Mail;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AjaxGetMailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String pageIndexString = req.getParameter(Tags.TAG_MAIL_PAGE_INDEX);
            if (pageIndexString.matches("^[0-9]*")) { // 页数输入检查
                int pageIndex = Integer.parseInt(pageIndexString);
                response = new Success(Mail.getMailsList(user.getId(), Mail.defaultPageSize, pageIndex));
            } else {
                response = new Failure("输入页数不合法");
            }
        } else {
            response = new Failure("用户未登录");
        }

        writer.write(Utils.getGson().toJson(response));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        User user = Utils.getCurrentUser(req);

        if (user != null) {
            String mailFrom = req.getParameter(Tags.TAG_MAIL_FROM);
            Timestamp mailDate = new Timestamp(Long.valueOf(req.getParameter(Tags.TAG_MAIL_DATE)));
            Mail.readMail(new Mail(mailFrom, user.getId(), "", mailDate, true));
        }
    }
}
