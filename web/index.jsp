<%--
  Created by IntelliJ IDEA.
  User: nakamuraryou
  Date: 2017/11/03
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "model.TodoModel" %>
<%
  //リクエストパラメータの文字コードを指定
  request.setCharacterEncoding("UTF-8");
%>
<html>
  <head>
    <title>ToDo</title>
  </head>
  <body>
    <h1>ToDo</h1>
    <a href="/userRegist">新規登録</a>
    <a href="/login">ログイン</a>
  </body>
</html>
