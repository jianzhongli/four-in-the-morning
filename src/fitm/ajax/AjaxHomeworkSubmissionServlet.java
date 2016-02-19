package fitm.ajax;

import fitm.controller.FitmPath;
import fitm.model.HomeworkSubmission;
import fitm.model.Student;
import fitm.model.User;
import fitm.util.Tags;
import fitm.util.Utils;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

@MultipartConfig
public class AjaxHomeworkSubmissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        User user = Utils.getCurrentUser(req);
        Response response;

        if (user != null) {
            String homework_id = req.getParameter(Tags.TAG_HOMEWORK_ID);
            Timestamp submit_date = new Timestamp(System.currentTimeMillis());
            Part file_part = req.getPart(Tags.TAG_ATTATCH_FILE);
            String attach_file = "";
            if (file_part != null) {
                String extension = Utils.getSubmittedFileNameExtension(file_part);
                attach_file = FitmPath.HOMEWORK_SUBMISSION_FILE_PATH + homework_id + '_' + user.getId() + extension;
                File file = new File(getServletContext().getRealPath("/") + attach_file);
                file.getParentFile().mkdirs();
                IOUtils.copy(file_part.getInputStream(), new FileOutputStream(file));
            }

            HomeworkSubmission submission = new HomeworkSubmission(homework_id, new Student(user), submit_date, attach_file, 0);
            if (HomeworkSubmission.insertHomeworkSubmission(submission)) {
                response = new Success(submission);
            } else {
                response = new Failure("数据库错误。");
            }
        } else {
            response = new Failure("用户未登录。");
        }
        writer.write(Utils.getGson().toJson(response));
    }
}
