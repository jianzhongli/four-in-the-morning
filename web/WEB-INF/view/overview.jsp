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
            <div class="row">
                <div class="col s8">
                    <div class="section">
                        <h5>最新动态</h5>
                        <div class="divider"></div>
                        <h6>${user.getName()} 43 分钟前 发布了作业</h6>

                        <div class="progress">
                            <div class="determinate" style="width: 70%"></div>
                        </div>

                        <p>Stuff</p>
                    </div>
                </div>
                <div class="col s4">
                    <div class="collection">
                        <a href="#" class="collection-item">交作业</a>
                        <a href="" class="collection-item">第二个操作</a>
                        <a href="" class="collection-item">第三个操作</a>
                        <a href="" class="collection-item">第四个操作</a>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col s12">
                    <ul class="collection with-header">
                        <li class="collection-header"><h4>First Names</h4></li>
                        <li class="collection-item">Alvin</li>
                        <li class="collection-item">Alvin</li>
                        <li class="collection-item">Alvin</li>
                        <li class="collection-item">Alvin</li>
                    </ul>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script src="../../js/header.js" type="application/javascript"></script>
    </body>
</html>