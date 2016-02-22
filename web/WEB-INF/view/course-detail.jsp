<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../../css/style.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>凌晨四点线上作业提交系统</title>
</head>

<body class="grey lighten-5">
<%@ include file="header.jsp" %>

<c:set var="isAssitant" value="${user.isAssistantOfCourse(course.course_id)}" />

<div class="container">
    <div class="card">
        <div class="card-content">
            <div class="section">
                <div class="row">
                    <div class="col s12">
                        <ul class="tabs">
                            <li class="tab col s3"><a class="active" href="#overview">课程总览</a></li>
                            <li class="tab col s3"><a href="#slides">课件下载</a></li>
                            <li class="tab col s3"><a href="#homework">查看作业</a></li>
                            <c:if test="${user.isTeacher() || isAssitant}">
                                <li class="tab col s3"><a href="#students">学生列表</a></li>
                            </c:if>
                        </ul>
                        <div class="divider"></div>
                    </div>
                </div>
            </div>

            <div class="row" >
                <div class="col s12" id="overview">
                    <div class="section">
                        <h3>
                            ${course.course_name}
                        </h3>
                    </div>
                    <div class="section">
                        <h4>
                            课程简介
                            <c:if test="${user.isTeacher()}">
                                <a id="button-edit-intro" class="btn-floating waves-effect waves-light" onclick="open_modal_edit_intro()">
                                    <i class="material-icons">mode_edit</i>
                                </a>
                            </c:if>
                        </h4>
                        <div class="divider"></div>
                        ${course.getHtmlIntroText()}
                    </div>
                    <div class="section">
                        <h4>
                            助教名单
                            <a id="button-assign-ta" class="btn-floating waves-effect waves-light" onclick="open_modal_assign_ta()">
                                <i class="material-icons">add</i>
                            </a>
                        </h4>
                        <div class="divider"></div>
                        <c:forEach var="teaching_class" items="${course.classes}">
                            <h5>${teaching_class.class_name}</h5>
                            <c:choose>
                                <c:when test="${teaching_class.ta_list != null && !teaching_class.ta_list.isEmpty()}">
                                    <c:forEach var="ta" items="${teaching_class.ta_list}">
                                        <div class="chip">
                                            ${ta.name}
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    暂时没有助教。
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <%--课件下载 TODO: 在数据库里补充这一部分--%>
            <%@ include file="slides.jsp" %>

            <%--作业情况--%>
            <%@ include file="homework.jsp" %>

            <%--如果当前用户是老师或助教，显示教学班列表--%>
            <c:if test="${user.isTeacher() || isAssitant}">
                <div class="row">
                    <div class="col s12" id="students">
                        <c:forEach var="teaching_class" items="${course.classes}">
                            <div class="section">
                                <h5>${teaching_class.class_name}</h5>
                                <div class="divider"></div>
                                <table class="highlight centered bordered">
                                    <thead>
                                    <tr>
                                        <th data-field="name">姓名</th>
                                        <th data-field="id">学号</th>
                                        <th data-field="email">邮箱</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="student" items="${teaching_class.getStudents()}">
                                        <tr><td>${student.name}</td><td>${student.id}</td><td>待添加邮箱</td></tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="modal-edit-course-intro.jsp" %>
<%@ include file="modal-assign-ta.jsp" %>
<script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../../js/materialize.min.js"></script>
<script src="../../js/header.js" type="application/javascript"></script>
<script src="../../js/course-detail.js" type="application/javascript"></script>
<script type="text/javascript" async
        src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML">
</script>
</body>
</html>