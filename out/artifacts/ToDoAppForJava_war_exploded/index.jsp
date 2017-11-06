<%--
  Created by IntelliJ IDEA.
  User: nakamuraryou
  Date: 2017/11/03
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>ToDo</title>
  </head>
  <body>
  <h1>ToDo</h1>
  <table>
    <tr>
      <th>作業内容</th>
      <th>状況</th>
      <th>メモ</th>
    </tr>
    <tr>
      <td><input type="text" value=<%= request.getAttribute("work")%>></td>
      <td>
        <select>
          <option value="1">未着手</option>
          <option value="2">着手中</option>
          <option value="3">完了</option>
        </select>
      </td>
      <td><input type="text" value=<%= request.getAttribute("memo")%>></td>
    </tr>
  </table>
  <a href="/todo">リンク</a>
  </body>
</html>
