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
        <jsp:include page="header.jsp" />

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
                                        <a class="btn waves-effect waves-light">发送</a>
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
                            <div class="row">
                                <div class="col s12">
                                    <ul class="collapsible popout" data-collapsible="accordion">
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="collapsible-header">
                                                <span>李四<i class="material-icons">perm_identity</i></span>
                                            </div>
                                            <div class="collapsible-body">
                                                <p>One common flaw we've seen in many frameworks is a lack of support for truly responsive text. While elements on the page resize fluidly, text still resizes on a fixed basis. To ameliorate this problem, for text heavy pages, we've created a class that fluidly scales text size and line-height to optimize readability for the user. Line length stays between 45-80 characters and line height scales to be larger on smaller screens. To see Flow Text in action, slowly resize your browser and watch the size of this text body change! Use the button above to toggle off/on flow-text to see the difference</p>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col s12">
                                    <ul class="pagination center-align">
                                        <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
                                        <li class="active"><a href="#!">1</a></li>
                                        <li class="waves-effect"><a href="#!">2</a></li>
                                        <li class="waves-effect"><a href="#!">3</a></li>
                                        <li class="waves-effect"><a href="#!">4</a></li>
                                        <li class="waves-effect"><a href="#!">5</a></li>
                                        <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
        <script type="text/javascript" src="../../js/materialize.min.js"></script>
        <script src="../../js/header.js" type="application/javascript"></script>
        <script type="text/javascript" src="../../js/mailbox.js"></script>
    </body>
</html>
