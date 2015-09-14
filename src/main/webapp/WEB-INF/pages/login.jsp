<%--
  Created by IntelliJ IDEA.
  User: x0r
  Date: 14/09/15
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Here</title>
</head>
<body>

<form method="post">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}">
    <input type="text" name="username"/>
    <input type="password" name="password"/>

    <input type="submit" value="Login"/>
</form>

</body>
</html>
