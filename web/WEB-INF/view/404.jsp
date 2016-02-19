<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="../../css/style.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>凌晨四点线上作业提交系统</title>
        <style type="text/css">
            #error_card {
                margin-top: 10em;
            }
        </style>
    </head>

    <body class="grey lighten-5">
        <%@ include file="header.jsp" %>

        <div class="container">
            <div class="row" id="error_card">
                <div class="col l8 offset-l2">
                    <div class="card-panel hoverable indigo lighten-1">
                        <h1 class="white-text valign">404 Not Found</h1>
                        <span class="white-text valign">抱歉, 链接异常或该页面还没完成</span>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script src="../../js/header.js" type="application/javascript"></script>
    </body>
</html>