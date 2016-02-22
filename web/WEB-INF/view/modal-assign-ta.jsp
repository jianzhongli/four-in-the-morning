<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="modal-assign-ta" class="modal">
    <div class="modal-content">
        <div class="row">
            <div class="col s12">
                <h5>新增助教</h5>
                <div class="divider"></div>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s6">
                <input placeholder="在此输入学号" id="ta_id" type="text" class="validate">
                <label for="ta_id">助教学号</label>
            </div>
            <div class="input-field col s6">
                <input placeholder="助教姓名" id="ta_name" type="text" class="validate" disabled="disabled">
                <label for="ta_name">助教姓名</label>
            </div>
        </div>
        <div class="row">
            <div class="col s12">
                <h5>负责班级</h5>
                <div class="divider"></div>
            </div>
        </div>
        <div class="row">
            <c:forEach var="teaching_class" items="${course.classes}">
                <div class="input-field col">
                    <input type="checkbox" class="filled-in" name="checkbox_classes" id="${teaching_class.class_id}" value="${teaching_class.class_id}"/>
                    <label for="${teaching_class.class_id}">${teaching_class.class_name}</label>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="modal-footer">
        <a class="modal-action modal-close waves-effect waves-light btn" onclick="send_ta_assignment_to_server()">
            确认
        </a>
        <a class="modal-action modal-close btn-flat grey-text">取消</a>
    </div>
</div>

