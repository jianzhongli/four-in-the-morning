<%@ page contentType="text/html;charset=UTF-8" session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" href="../../static/mdl/material.min.css" type="text/css">
        <link rel="stylesheet" href="../../static/material-icons.css" type="text/css">
        <script src="../../static/mdl/material.min.js" type="application/javascript"></script>
        <script src="../../static/main.js" type="application/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>我的作业</title>
    </head>
    <body>
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

            <jsp:include page="header.jsp" />

            <main class="mdl-layout__content mdl-color--grey-100">
                <div class="page-content">
                    <div class="mdl-grid">
                        <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--8-col">
                            <div class="mdl-card__title mdl-card--expand">
                                <h4>
                                    Featured event:<br>
                                    May 24, 2016<br>
                                    7-11pm
                                </h4>
                            </div>
                            <div class="mdl-card__actions mdl-card--border">
                                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                                    Add to Calendar
                                </a>
                            </div>
                        </div>
                        <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--4-col">
                            <div class="mdl-card__title mdl-card--expand">
                                <h4>
                                    Featured event:<br>
                                    May 24, 2016<br>
                                    7-11pm
                                </h4>
                            </div>
                            <div class="mdl-card__actions mdl-card--border">
                                <a class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                                    Add to Calendar
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>

