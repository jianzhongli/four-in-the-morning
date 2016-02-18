<%--
  Created by IntelliJ IDEA.
  User: Brian
  Date: 2/18/2016
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="../../css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="../../css/style.css"/>
    <link type="text/css" rel="stylesheet" href="../../css/promotion.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>凌晨四点线上作业提交系统</title>
</head>
<body>
    <div class="parallax-container">
        <div class="section no-pad-bot">
            <div class="container">
                <br><br>
                <h1 class="header center">凌晨四点</h1>
                <div class="row center">
                    <h5 class="header col s12 grey-text">
                        作为 Computer Science 的学生，凌晨四点之前睡都应该感到羞耻。
                    </h5>
                </div>
                <div class="row center">
                    <a href="/login" class="btn-large waves-effect waves-light">开始使用</a>
                </div>
                <br><br>
            </div>
        </div>

        <div class="parallax"><img src="../../img/city-cars-traffic-lights.jpeg"></div>
    </div>

    <div class="container">
        <div class="section">

            <!--   Icon Section   -->
            <div class="row">
                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center brown-text"><i class="material-icons">flash_on</i></h2>
                        <h5 class="center">加速作业发布与提交的流程</h5>

                        <p>不会再弄错 FTP 文件夹和邮件收件人，也不需要人工统计作业提交情况，利用系统提供的 API，甚至还可以将评分过程自动化。凌晨四点，给你更顺滑的赶 Deadline 体验。</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center brown-text"><i class="material-icons">group</i></h2>
                        <h5 class="center">友好的用户界面</h5>

                        <p>受够了那些诞生于远古时期的爷爷级系统？全站遵循 <a href="https://www.google.com/design/spec/material-design/introduction.html">Google Material Design Guideline</a>，推荐使用 Chrome 或 Firefox 浏览器访问本站以获得最佳使用体验。</p>
                    </div>
                </div>

                <div class="col s12 m4">
                    <div class="icon-block">
                        <h2 class="center brown-text"><i class="material-icons">settings</i></h2>
                        <h5 class="center">易于拓展的第三方支持</h5>

                        <p>凌晨四点提供了简单易用的 <a href="https://github.com/kexanie/four-in-the-morning/wiki/%E5%87%8C%E6%99%A8%E5%9B%9B%E7%82%B9-API-v0.1">RESTful API</a>，开发者可以自由地利用它们来构建自己的第三方应用。课上学到了新技能？用我们的 API 来练练手吧！</p>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <footer class="page-footer teal">
        <div class="container">
            <div class="row">
                <div class="col l6 s12">
                    <h5 class="white-text">凌晨四点</h5>
                    <p class="grey-text text-lighten-4">一个为移动信息工程学院量身定制的在线作业提交系统。</p>


                </div>
                <div class="col l3 s12">
                    <h5 class="white-text">关于本站</h5>
                    <ul>
                        <li><a class="white-text" href="https://github.com/kexanie/four-in-the-morning">项目源码</a></li>
                        <li><a class="white-text" href="https://github.com/kexanie/four-in-the-morning/wiki/%E5%87%8C%E6%99%A8%E5%9B%9B%E7%82%B9-API-v0.1">开发者</a></li>
                        <li><a class="white-text" href="#!">使用帮助</a></li>
                        <li><a class="white-text" href="https://github.com/kexanie/four-in-the-morning/issues">建议反馈</a></li>
                    </ul>
                </div>
                <div class="col l3 s12">
                    <h5 class="white-text">友情链接</h5>
                    <ul>
                        <li><a class="white-text" href="http://www.sysu.edu.cn/2012/cn/index.htm">中山大学</a></li>
                        <li><a class="white-text" href="http://smie.sysu.edu.cn/">移动信息工程学院</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="footer-copyright">
            <div class="container">
                Made by <a class="brown-text text-lighten-3" href="https://github.com/four-in-the-morning">Four In The Morning</a>
            </div>
        </div>
    </footer>

    <script type="text/javascript" src="../../js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="../../js/materialize.min.js"></script>
    <script type="text/javascript" src="../../js/promotion.js"></script>
</body>
</html>
