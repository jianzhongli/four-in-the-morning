package fitm.ajax;

import fitm.model.Mail;
import fitm.util.Tags;
import fitm.util.Utils;
import fitm.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxSendMailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String mail_from = user.getName() + " " + user.getId();
            String mail_to = req.getParameter(Tags.TAG_MAIL_TO);
            String mail_content = req.getParameter(Tags.TAG_MAIL_CONTENT);

            if (User.getUserById(mail_to) == null) { // 参数检查：收件人存在
                response = new Failure("收件人不存在");
            } else if (mail_content != null && mail_content.isEmpty()) { // 参数检查：邮件内容合法
                response = new Failure("请填写邮件正文");
            } else {
                Mail mail = new Mail(mail_from, mail_to, mail_content, false);
                if (Mail.sendMail(mail)) {
                    response = new Success(mail);
                } else {
                    response = new Failure("数据库错误");
                }
            }
        } else {
            response = new Failure("用户未登录");
        }

        writer.write(Utils.getGson().toJson(response));
    }
}
