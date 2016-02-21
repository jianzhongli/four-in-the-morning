<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modal-edit-course-intro" class="modal modal-fixed-footer">
    <div class="modal-content">
        <div class="row">
            <div class="col s12">
                <h5>课程简介</h5>
                <div class="divider"></div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <textarea id="intro-text" class="materialize-textarea" placeholder="在此输入课程简介（支持 Markdown）"></textarea>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <a class="modal-action modal-close waves-effect waves-light btn" onclick="update_intro_text()">
            确认
        </a>
        <a class="modal-action modal-close btn-flat grey-text">取消</a>
    </div>
</div>

