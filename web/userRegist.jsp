<%--
  Created by IntelliJ IDEA.
  User: nakamuraryou
  Date: 2017/11/03
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "java.util.List" %>
<%
  //リクエストパラメータの文字コードを指定
  request.setCharacterEncoding("UTF-8");
%>
<html>
  <head>
    <title>ToDo-アカウント登録</title>
  </head>
  <body>
  <h1>アカウント登録</h1>
  <form method="post" action="userRegist">
    <% if (request.getAttribute("error") != null) {
       List<String> error =  (List<String>)request.getAttribute("error");
       for(String e : error) {%>
    <p style="color:#ff0000"><%=e%></p>
    <%  }
    }%>
    <table>
      <tr>
        <th>メールアドレス</th>
        <td><input type="text" name="mail" value=<%=request.getAttribute("mail")!=null?request.getAttribute("mail"):""%>></td>
      </tr>
      <tr>
        <th>名前</th>
        <td><input type="text" name="name" value=<%=request.getAttribute("name")!=null?request.getAttribute("name"):""%>></td>
      </tr>
      <tr>
        <th>パスワード</th>
        <td><input type="password" name="password" value=<%=request.getAttribute("password")!=null?request.getAttribute("password"):""%>></td>
      </tr>
    </table>
    <p><button type="submit" name="regist">登録</button></p>
  </form>
  </body>
</html>
