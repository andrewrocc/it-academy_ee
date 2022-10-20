<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
    <head>
        <title>Login</title>
    </head>
    <h1>Login</h1>
    <body>
        <form action="/hello/login.do" method="POST">
            <label for="username">Enter your username:</label><br>
            <input type="text" id="usesrname" name="username"><br>
            <label for="userphone">Enter your phone number:</label><br>
            <input type="text" id="userphone" name="userphone"><br>
            <label for="useremail">Enter your email:</label><br>
            <input type="text" id="useremail" name="useremail"><br><br>
        
            <input type="submit" value="Log in!">
            <p><c:out value="${message}"/></p>
            <c:out value="${userName}"/></br>
            <c:out value="${userPhone}"/></br>
            <c:out value="${userEmail}"/></br>
        
        </form>
    </body>

</html>