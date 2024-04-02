<%--
  Created by IntelliJ IDEA.
  User: quang
  Date: 2/04/2024
  Time: 10:53 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to Quang Nguyen page</title>
</head>
<body>
<h1><%= "Important Form" %>
</h1>
<br/>
<form method="post" action="myServlet" autocomplete="off">
    <label for="name">What's your name?</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="favFruit">What's your favorite fruit?</label><br>
    <input type="text" id="favFruit" name="favFruit"><br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
