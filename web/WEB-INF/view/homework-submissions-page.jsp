<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../../css/style.css" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>凌晨四点线上作业提交系统</title>
</head>

<%--如果是学生，先判断是不是这门课的 TA，下面就不用重复调用函数 isAssistantOfCourse()--%>
<c:if test="${user.isStudent()}">
    <c:set var="isAssitant" value="${user.isAssistantOfCourse(course.course_id)}" />
</c:if>

<body class="grey lighten-5">
<jsp:include page="header.jsp" />

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
                <h5>提交情况</h5>
                <div class="divider"></div>
                <table class="highlight centered bordered">
                    <thead>
                    <tr>
                        <th data-field="name">姓名</th>
                        <th data-field="id">学号</th>
                        <th data-field="email">邮箱</th>
                        <th data-field="attach_file">提交情况</th>
                    </tr>
                    </thead>
                    <c:forEach var="submission" items="${homework_submission_list}">
                        <tr>
                            <td>${submission.student.name}</td>
                            <td>${submission.student.id}</td>
                            <td>待添加</td>
                            <td><a class="btn waves-effect waves-light" href="${submission.attach_file}">点击下载</a></td>
                        </tr>
                    </c:forEach>
                </table>
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