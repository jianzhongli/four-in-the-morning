<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modal-post-homework" class="modal modal-fixed-footer">
    <div class="modal-content">
        <h5>发布作业</h5>
        <form enctype="multipart/form-data">
            <input id="course-id" type="hidden" value="${course.getCourse_id()}"></input>
            <div class="row">
                <div class="input-field col s12">
                    <input id="homework_title" type="text">
                    <label for="homework_title">作业标题</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12">
                    <textarea id="homework_description" class="materialize-textarea"></textarea>
                    <label for="homework_description">作业描述（支持 LaTeX 代码）</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s5">
                    <input type="date" class="datepicker" id="ddl">
                    <label for="ddl">点击选择期限</label>
                </div>
                <div class="file-field input-field col s7">
                    <div class="btn">
                        <span>附件</span>
                        <input id="attach_file" type="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a class="modal-action modal-close waves-effect waves-light btn" onclick="post_homework_to_server()">提交</a>
        <a class="modal-action modal-close btn-flat" href="#">取消</a>
    </div>
</div>
