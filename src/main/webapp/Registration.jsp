<%--
  Created by IntelliJ IDEA.
  User: NhatAnh
  Date: 19-Jan-23
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Registration page</h1>
<h2>${result}</h2>
<form method="POST" action="/RegistrationServlet">
    <label name="username">Username</label>
    <input type="text" name="username">
    <br>
    <label name="password">Password</label>
    <input type="password" name="password">
    <br>
    <label name="confirm_password">Password confirmation</label>
    <input type="password" name="confirm_password">
    <br>
    <button type="submit">Submit</button>
</form>
</body>
</html>
