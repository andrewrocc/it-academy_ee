<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <title>Detect user browser</title>
    </head>
    <h2>Detect browser</h2>
    <body>
        <form action="/hello/detect.do" method="POST">
        <input type="submit" value="Get info!">
            <p><c:out value="${userAgent}"/></p>
        </form>
    </body>

</html>