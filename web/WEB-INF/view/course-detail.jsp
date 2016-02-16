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

            <%--TODO：在数据库里补充相关字段--%>
            <div class="row" >
                <div class="col s12" id="overview">
                    <div class="section">
                        <h4>${course.getCourse_name()}</h4>
                    </div>
                    <div class="section">
                        <h5>Introdution</h5>
                        <div class="divider"></div>
                        <blockquote>
                            这些是假的。
                            Artificial Intelligence is the science of getting computers to act without being explicitly programmed. This course provides a fundamental overview of artificial intelligence, machine learning and data mining, as well as in-depth discussions and applications on learning algorithms. The topics include propositional logic, first-order predicate logic, k-nearest neighbor, naїve Bayesian classifier, decision tree, perceptron learning algorithm, least squares for classification, logistic regression, neural networks, support vector machines, association rule mining, partitional and hierarchical clustering, density-based clustering, in addition to typical practices, case studies and applications. Students are expected to learn how to apply learning algorithms to text understanding, computer vision, medical informatics, database mining, and other areas.
                        </blockquote>
                    </div>
                    <div class="section">
                        <h5>Instructor</h5>
                        <div class="divider"></div>
                        <p>
                            A/Prof. Yanghui Rao这些是假的。
                        </p>
                        <p>
                            这些是假的。
                            Office: Room 409, 4th floor of Administrative Building, Sun Yat-sen University Zhuhai Campus
                        </p>
                        <p>
                            这些是假的。
                            Homepage : http://smie2.sysu.edu.cn/~ryh/ai/index.html
                        </p>
                        <p>
                            这些是假的。
                            Email : raoyangh@mail.sysu.edu.cn
                        </p>
                    </div>
                    <div class="section">
                        <h5>Reference
                        </h5>
                        <div class="divider"></div>
                        <p>
                            [1] A. Smola and S. V. N. Vishwanathan. Introduction to Machine Learning, Cambridge University Press, 2010.
                            [2] J. Han, M. Kamber, J. Pei. Data Mining: Concepts and Techniques, 3rd Edition, Morgan Kaufmann Publishers, 2011.
                            [3] C. D. Manning, P. Raghavan, H. Schütze. An Introduction to Information Retrieval, Cambridge University Press, 2009.
                            [4] T. Hastie, R. Tibshirani, J. Friedman. The Elements of Statistical Learning: Data Mining, Inference, and Prediction, 2nd Edition, Springer, 2009.
                            [5] C. M. Bishop. Pattern Recognition and Machine Learning, Springer, 2006.
                            [6]	P. Giudici. Applied Data Mining: Statistical Methods for Business and Industry, Wiley, 2003.        </p>
                        <p>
                    </div>
                </div>
            </div>

            <%--课件下载 TODO: 在数据库里补充这一部分--%>
            <jsp:include page="slides.jsp" />

            <%--作业情况--%>
            <jsp:include page="homework.jsp"/>

            <%--如果当前用户是老师，显示教学班列表--%>
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

<jsp:include page="modal-post-homework.jsp" />
<jsp:include page="modal-delete-confirm.jsp" />
<script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../../js/materialize.min.js"></script>
<script src="../../js/header.js" type="application/javascript"></script>
<script src="../../js/course-detail.js" type="application/javascript"></script>
<script type="text/javascript" async
        src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-MML-AM_CHTML">
</script>
</body>
</html>