package fitm.ajax;

import fitm.controller.Path;
import fitm.model.HomeworkPost;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@MultipartConfig
public class AjaxPostHomeworkServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        Response response;

        if (Utils.hasLogin(req)) {
            String course_id = req.getParameter(Tags.TAG_COURSE_ID);
            String homework_title = req.getParameter(Tags.TAG_HOMEWORK_TITLE);
            String homework_description = req.getParameter(Tags.TAG_HOMEWORK_DESCRIPTION);
            Date post_date = Utils.parseDate(req.getParameter(Tags.TAG_POST_DATE));
            Date ddl = Utils.parseDate(req.getParameter(Tags.TAG_DDL));

            Part file_part = req.getPart(Tags.TAG_ATTATCH_FILE);
            String extension = Utils.getSubmittedFileNameExtension(file_part);
            String homework_id = course_id + System.currentTimeMillis();
            String attach_file = Path.HOMEWORK_POST_FILE_PATH + homework_id + extension;
            IOUtils.copy(file_part.getInputStream(), new FileOutputStream(attach_file));

            HomeworkPost homeworkPost =
                    new HomeworkPost(course_id, homework_id, homework_title, homework_description, attach_file, post_date, ddl);
            if (SQLHelper.getInstance().insertHomeworkPost(homeworkPost)) {
                response = new Success(homeworkPost);
            } else {
                response = new Failure("数据库错误。");
            }

        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }
}
