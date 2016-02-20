<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="homework">
    <c:if test="${user.isTeacher() || isAssitant}">
        <div class="row">
            <div class="col offset-l1">
                <a class="waves-effect waves-light btn" onclick="post_new_homework()">发布新作业 </a>
            </div>
        </div>
    </c:if>
    <div class="row">
        <div class="col s12 l10 offset-l1" >
            <c:if test="${homework_list.size() > 0}">
                <ul class="collapsible popout" data-collapsible="accordion">
                    <c:forEach var="homework" items="${homework_list}" varStatus = "status">
                        <li>
                            <div class="collapsible-header ${status.first ? 'active' : ''}">
                                    ${homework.getHomework_title()}
                            </div>
                            <div class="collapsible-body">
                                <p class="homework-post-body">${homework.getHomework_description()}</p>
                                <c:if test="${!homework.getAttach_file().isEmpty()}">
                                    <a class="btn waves-effect waves-light homework-post-action" href="${homework.getAttach_file()}">下载附件</a>
                                </c:if>


                                <div class="divider"></div>
                                <div style="width: 100%">
                                    <div class="right-align">
                                        <c:choose>
                                            <c:when test="${user.isTeacher() || isAssitant}">
                                                <a class="btn red waves-effect waves-light homework-post-action" onclick="delete_confirm('${homework.homework_id}')">删除</a>
                                                <a class="btn waves-effect waves-light homework-post-action" onclick="edit_homework('${homework.homework_id}')">修改</a>
                                                <a class="btn waves-effect waves-light homework-post-action" href="/homework/${homework.homework_id}">查看更多</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a class="btn waves-effect waves-light homework-post-action" onclick="open_submit_homework_modal('${homework.homework_id}')">交作业</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="modal-submit-homework.jsp" %>
<%@ include file="modal-post-homework.jsp"  %>
<%@ include file="modal-delete-confirm.jsp" %>