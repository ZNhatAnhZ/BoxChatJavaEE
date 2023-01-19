<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1>Login page</h1>
<h2>${result}</h2>
<form method="POST" action="/LoginServlet">
    <label name="username">Username</label>
    <input type="text" name="username">
    <br>
    <label name="password">Password</label>
    <input type="password" name="password">
    <br>
    <button type="submit">Submit</button>
</form>
<br/>
<a href="Registration.jsp">Register here!!!</a>
</body>
</html>