<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
       <title>Книги</title>
</head>
<body>
<h3>Книги</h3>
<%--<a href="<c:url value="/addUser.html"/>">Добавить пользователя</a>--%>
 
 <table>
     <thead>
 <tr>
         <td>Название</td>
         <td>Автор</td>
       </tr>
 </thead>
       <c:forEach items="${books}" var="book">
       <tr>
            <td>1.${book.name}</td>
            <td>2.${book.author}</td>
    <%--     <td><a href="mailto:${user.email}">${user.email}</a></td>--%>
    <%--     <td><fmt:formatDate value="${user.birthDate}" pattern="dd-MM-yyyy"/></td>--%>
    <%--     <td>${user.active ? "Активен" : "Деактивирован"}</td>--%>
    <%--     <td><a href="<c:url value="/editUser.html?id=${user.id}"/>">Редактировать</a></td>--%>
       </tr>
       </c:forEach>
     </table>
</body>
</html>