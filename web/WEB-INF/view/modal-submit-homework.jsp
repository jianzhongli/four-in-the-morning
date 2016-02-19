<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modal-submit-homework" class="modal">
    <div class="modal-content">
        <h5>提交作业</h5>
        <form enctype="multipart/form-data">
            <div class="row">
                <div class="file-field input-field col s12">
                    <div class="btn">
                        <span>点击上传作业附件</span>
                        <input id="submission_attach_file" type="file">
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text">
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="modal-footer">
        <a class="modal-action modal-close waves-effect waves-light btn" onclick="submit_homework_to_server()">提交</a>
        <a class="modal-action modal-close btn-flat" href="#">取消</a>
    </div>
</div>
