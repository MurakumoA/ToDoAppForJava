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
  <form method="post" action="todo">
    <table>
      <tr>
        <th>作業内容</th>
        <th>状況</th>
        <th>メモ</th>
        <th>開始予定日</th>
        <th>終了予定日</th>
      </tr>
      <tr>
        <td><input type="text" name="work" value=<%= request.getAttribute("work")%>></td>
        <td>
          <select name="cond">
            <option value="1">未着手</option>
            <option value="2">着手中</option>
            <option value="3">完了</option>
          </select>
        </td>
        <td><input type="text" name="memo" value=<%= request.getAttribute("memo")%>></td>
        <td><input type="date" name="startDate" value=<%= request.getAttribute("startDate")%>></td>
        <td><input type="date" name="endDate" value=<%= request.getAttribute("endDate")%>></td>
      </tr>
    </table>
    <p><button type="submit" value="add" onclick="alert('追加')">追加</button></p>
    <p><button type="submit" value="regist">登録</button></p>
  </form>
  </body>
</html>
