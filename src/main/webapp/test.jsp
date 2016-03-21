<%--
  Created by IntelliJ IDEA.
  User: aileci
  Date: 2015/6/3
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%out.println(request.getRequestURL());%>
<%out.println(request.getParameter("text"));%>
</body>
</html>
