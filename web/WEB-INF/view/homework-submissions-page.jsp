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

<div class="container">
    <div class="card">
        <div class="card-content">
            <div class="section">
                <h4>作业详情</h4>
                <div class="divider"></div>
                <h5>${homework_post.homework_title}</h5>
                <p>${homework_post.homework_description}</p>
            </div>
            <div class="section">
                <h4>提交情况</h4>
                <div class="divider"></div>
                <c:forEach var="teaching_class" items="${class_list}">
                    <div class="section">
                        <h5>
                            ${teaching_class.class_name}
                            <a class="btn-floating waves-effect waves-light tooltipped" data-position="right" data-tooltip="批量下载该教学班的学生作业">
                                <i class="material-icons">file_download</i>
                            </a>
                        </h5>
                    </div>
                    <table class="highlight centered bordered">
                        <thead>
                        <tr>
                            <th data-field="name">姓名</th>
                            <th data-field="id">学号</th>
                            <th data-field="email">邮箱</th>
                            <th data-field="attach_file">提交情况</th>
                        </tr>
                        </thead>
                        <c:forEach var="student" items="${teaching_class.students}">
                            <tr>
                                <td>${student.name}</td>
                                <td>${student.id}</td>
                                <td>待添加</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${homework_submission_map.get(student.id) != null}">
                                            <a class="btn waves-effect waves-light" href="${homework_submission_map.get(student.id).attach_file}">点击下载</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="red-text">未提交</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../../js/materialize.min.js"></script>
<script src="../../js/header.js" type="application/javascript"></script>
<script type="text/javascript" async
        src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML">
</script>
</body>
</html>