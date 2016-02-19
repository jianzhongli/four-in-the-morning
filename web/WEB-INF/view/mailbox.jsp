<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <div class="col s12 l4">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title"><h5>发送消息</h5></div>
                            <form enctype="text/plain">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <input type="text" id="mail_to">
                                        <label for="mail_to">收件人</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="input-field col s12">
                                        <textarea type="text" id="mail_content" class="materialize-textarea"></textarea>
                                        <label for="mail_content">邮件正文</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s6">
                                        添加表情
                                    </div>
                                    <div class="col s6">
                                        <a class="btn waves-effect waves-light" onclick="send_mail()">发送</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col s12 l8">
                    <div class="card">
                        <div class="card-content">
                            <div class="card-title"><h5>接收消息</h5></div>
                            <div id="hide_num_entries" class="hide"><c:out value="${requestScope.mail_list_info}"/></div>
                            <div class="row">
                                <div class="col s12" id="mail_list"></div>
                            </div>
                            <div class="row">
                                <div class="col s12">
                                    <ul id="Pagination" class="pagination center-align"></ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/jquery.pagination.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script type="text/javascript" src="../../js/header.js"></script>
        <script type="text/javascript" src="../../js/mailbox.js"></script>
    </body>
</html>
