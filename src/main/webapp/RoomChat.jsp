<%@ page import="model.Message" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
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
<h1 id ="username">Welcome ${user.getUsername()}</h1>
<div>
    <div id="message_display" style="min-height: 500px; min-width: 800px; border: solid; overflow: auto;">
        <%
            List<Message> messages = (List) request.getAttribute("messages");
           for(Message message: messages) { %>
                <p><%= message.getUsername() %> said: <%= message.getContent()%></p> <%
            };
        %>

    </div>
    <div>
        <label name="message">Message:</label>
        <input id = "message_input" type="text" name="message">
        <br>
        <button id = "submit_button" >Submit</button>
        <a href="/LogoutServlet">Logout</a>
    </div>
</div>

</body>
<script src="./js/webSocketClient.js"></script>
</html>
