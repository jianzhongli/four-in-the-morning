package fitm.ajax;

import fitm.controller.Path;
import fitm.model.HomeworkPost;
import fitm.model.User;
import fitm.util.SQLHelper;
import fitm.util.Tags;
import fitm.util.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

// TODO: 对参数合法性进行检查
@MultipartConfig
public class AjaxHomeworkPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String[] pathItems = req.getRequestURI().split("[/]");
            String homework_id = pathItems[pathItems.length-1];
            HomeworkPost homeworkPost = HomeworkPost.getHomeworkPostById(homework_id);
            if (homeworkPost != null) {
                response = new Success(homeworkPost);
            } else {
                response  = new Failure("作业不存在。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        boolean is_new = req.getParameter(Tags.TAG_HOMEWORK_ID) == null;
        Response response;

        if (user != null) {
            boolean flag;
            HomeworkPost homeworkPost = getHomeworkPostFromRequest(req);

            if (is_new) {
                flag = HomeworkPost.insertHomeworkPost(homeworkPost);
            } else {
                flag = HomeworkPost.updateHomeworkPost(homeworkPost);
            }

            if (flag) {
                response = new Success(homeworkPost);
            } else {
                response = new Failure("数据库错误。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            if (user.isTeacher()) {
                String[] pathItems = req.getRequestURI().split("[/]");
                String homework_id = pathItems[pathItems.length-1];
                if (HomeworkPost.deleteHomeworkPost(homework_id)) {
                    response = new Success(null);
                } else {
                    response = new Failure("数据库错误");
                }
            } else {
                response = new Failure("权限拒绝。");
            }
        } else {
            response = new Failure("用户未登录。");
        }

        writer.write(Utils.getGson().toJson(response));
    }

    private HomeworkPost getHomeworkPostFromRequest(HttpServletRequest req) throws ServletException, IOException {
        String course_id = req.getParameter(Tags.TAG_COURSE_ID);
        String homework_title = req.getParameter(Tags.TAG_HOMEWORK_TITLE);
        String homework_description = req.getParameter(Tags.TAG_HOMEWORK_DESCRIPTION);
        Timestamp post_date = new Timestamp(System.currentTimeMillis());
        Timestamp ddl = new Timestamp(Long.valueOf(req.getParameter(Tags.TAG_DDL)));

        String homework_id = req.getParameter(Tags.TAG_HOMEWORK_ID);
        if (homework_id == null) { // 若 homework_id 为 null，是新作业，不为 null 则是更新作业
            homework_id = course_id + System.currentTimeMillis();
        }

        String attach_file = "";

        // attach_file is optional, this part is not tested yet.
        Part file_part = req.getPart(Tags.TAG_ATTATCH_FILE);
        if (file_part != null) {
            String extension = Utils.getSubmittedFileNameExtension(file_part);
            attach_file = Path.HOMEWORK_POST_FILE_PATH + homework_id + extension;
            IOUtils.copy(file_part.getInputStream(), new FileOutputStream(attach_file));
        }


        return new HomeworkPost(course_id, homework_id, homework_title, homework_description, attach_file, post_date, ddl);
    }
}
