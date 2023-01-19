<%--
  Created by IntelliJ IDEA.
  User: NhatAnh
  Date: 19-Jan-23
  Time: 8:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room Chat</title>
</head>
<body>
<h1>Welcome ${user.getUsername()}</h1>
<div>
    <div style="height: 500px; width: 800px; border: solid">
        <p>someone's name said: lmao</p>
    </div>
    <div>
        <form method="POST" action="/RoomChatServlet">
            <label name="message">Message:</label>
            <input type="text" name="message">
            <br>
            <button type="submit">Submit</button>
        </form>
        <a href="/LogoutServlet">Logout</a>
    </div>
</div>

</body>
</html>
