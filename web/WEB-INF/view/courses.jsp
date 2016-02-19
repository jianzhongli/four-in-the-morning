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
            <div class="section">
                <h5>我的课程</h5>
                <div class="divider"></div>
                <div class="row">
                    <c:forEach items="${course_list}" var="course">
                        <div class="col">
                            <div class="card hoverable">
                                <div class="card-content">
                                    <div class="card-title">${course.course_name}</div>
                                    <p>${course.teacher.name}</p>
                                </div>
                                <div class="card-action">
                                    <a href="/courses/${course.course_id}">查看详情</a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <c:if test="${user.isAssistant()}">
                <div class="section">
                    <h5>助教课程</h5>
                    <div class="divider"></div>
                    <div class="row">
                        <c:forEach var="assistant_course" items="${assistant_course_list}">
                            <div class="col">
                                <div class="card hoverable">
                                    <div class="card-content">
                                        <div class="card-title">${assistant_course.course_name}</div>
                                        <p>${assistant_course.teacher.name}</p>
                                    </div>
                                    <div class="card-action">
                                        <a href="/courses/${assistant_course.course_id}">查看详情</a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script src="../../js/header.js" type="application/javascript"></script>
    </body>
</html>