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
<jsp:include page="header.jsp" />

<div class="container">
    <div class="section">
        <div class="row">
            <div class="col s12">
                <ul class="tabs grey lighten-5">
                    <li class="tab col s3"><a class="active" href="#overview">课程总览</a></li>
                    <li class="tab col s3"><a href="#slides">课件下载</a></li>
                    <li class="tab col s3"><a href="#homework">查看作业</a></li>
                    <c:if test="${user.getUserType() < 2}">
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
    <div class="row">
        <div class="col s12" id="slides">
            <div class="row">
                待补充。
            </div>
        </div>
    </div>

    <%--作业情况--%>
    <div class="row">
        <div class="col s12 l10 offset-l1" id="homework">
            <c:if test="${homework_list.size() > 0}">
                <ul class="collapsible z-depth-0" data-collapsible="expandable">
                    <c:forEach var="homework" items="${homework_list}">
                        <li>
                            <div class="collapsible-header">
                                    ${homework.getHomework_title()}
                            </div>
                            <div class="collapsible-body">
                                <p>${homework.getHomework_description()}</p>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>

            <c:if test="${user.getUserType() < 2}">
                <div class="card">
                    <div class="card-content">
                        <div class="card-title"><h4>发布作业</h4></div>
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
                                    <label for="homework_description">作业描述</label>
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
                        <a class="btn waves-effect waves-light" onclick="post_homework()">提交</a>
                        <a class="btn-flat waves-effect waves-ripple">取消</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <%--如果当前用户是老师，显示教学班列表--%>
    <c:if test="${user.getUserType() < 2}">
        <div class="row">
            <div class="col s12" id="students">
                <c:forEach var="teaching_class" items="${course.getClasses()}">
                    <div class="section">
                        <h5>${teaching_class.getClass_name()}</h5>
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
                                    <tr><td>${student.getName()}</td><td>${student.getId()}</td><td>待添加邮箱</td></tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>
</div>

<script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../../js/materialize.min.js"></script>
<script src="../../js/header.js" type="application/javascript"></script>
<script src="../../js/course-detail.js" type="application/javascript"></script>
</body>
</html>