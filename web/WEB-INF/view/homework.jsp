<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="../../css/style.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

        <title>凌晨四点线上作业提交系统</title>
    </head>

    <body>
        <jsp:include page="header.jsp" />

        <div class="container">
            <div class="row">
                <div class="col s8 offset-s2">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title"><h4>发布作业</h4></div>
                            <form enctype="multipart/form-data">
                                <div  class="row">
                                    <div class="input-field col s12">
                                        <select id="select-course">
                                            <option value="" disabled selected>选择课程</option>
                                            <option value="1">编译原理</option>
                                            <option value="2">操作系统</option>
                                            <option value="3">人工智能</option>
                                        </select>
                                        <label for="select-course">课程</label>
                                    </div>
                                </div>
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
                                        <input type="date" class="datepicker" id="post_date">
                                        <label for="post_date">点击选择期限</label>
                                    </div>
                                    <div class="file-field input-field col s7">
                                        <div class="btn">
                                            <span>附件</span>
                                            <input type="file">
                                        </div>
                                        <div class="file-path-wrapper">
                                            <input class="file-path validate" type="text">
                                        </div>
                                    </div>
                                </div>
                            </form>
                            <a class="btn waves-effect waves-light">提交</a> <a class="btn-flat waves-effect waves-ripple">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script src="../../js/main.js" type="application/javascript"></script>
    </body>
</html>