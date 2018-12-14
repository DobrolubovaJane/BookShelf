<%--
  Created by IntelliJ IDEA.
  User: Jane
  Date: 14.12.2018
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form name="book" action="/addBook" method="post">
    <p>Name</p>
    <input title="Name" type="text" name="name">
    <p>Author</p>
    <input title="Author" type="text" name="author">
    <input type="submit" value="OK">
</form>
</body>
</html>
