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
  <p>
    <form method="get" action="logout">
      <%=request.getSession(true).getAttribute("name") + "さん"%>
      <button type="submit" name="logout">ログアウト</button>
    </form>
  </p>
  <input type="hidden" name="userId" value=<%=request.getSession(true).getAttribute("userId")%>>
  <form method="post" action="todo">
    <table>
      <tr>
        <th>削除</th>
        <th>作業内容</th>
        <th>状況</th>
        <th>メモ</th>
        <th>開始予定日</th>
        <th>終了予定日</th>
        <th></th>
      </tr>
      <% TodoModel model = (TodoModel)request.getAttribute("model");%>
      <% if(model != null && model.getId().size() > 0) { %>
      <input type="hidden" name="count" value=<%= model.getId().size() %>>
      <% for(int i = 0; i < model.getId().size(); i++) {%>
      <tr>
        <input type="hidden" name=<%="id" + i%> value=<%= (String)model.getId().get(i) %>>
        <td><input type="checkbox" name=<%="del" + i%>></td>
        <td><input type="text" name=<%="work" + i%> value=<%= (String)model.getWork().get(i)%>></td>
        <td>
          <select name=<%="cond" + i%>>
            <% switch(Integer.parseInt(model.getCond().get(i))) {
              case 1:%>
            <option value="1" selected>未着手</option>
            <option value="2">着手中</option>
            <option value="3">完了</option>
            <% break;
              case 2: %>
            <option value="1">未着手</option>
            <option value="2" selected>着手中</option>
            <option value="3">完了</option>
            <% break;
              case 3: %>
            <option value="1">未着手</option>
            <option value="2">着手中</option>
            <option value="3" selected>完了</option>
            <% break;
              default: %>
            <option value="1">未着手</option>
            <option value="2">着手中</option>
            <option value="3">完了</option>
            <% } %>
          </select>
        </td>
        <td><input type="text" name=<%="memo" + i%> value=<%= (String)model.getMemo().get(i)%>></td>
        <td><input type="date" name=<%="startDate" + i%> value=<%= (String)model.getStartDate().get(i)%>></td>
        <td><input type="date" name=<%="endDate" + i%> value=<%= (String)model.getEndDate().get(i)%>></td>
      </tr>
      <% } %>
      <% } else { %>
      <input type="hidden" name="count" value="1">
      <input type="hidden" name="id0" value="">
      <td><input type="checkbox" name="del0"></td>
      <td><input type="text" name="work0"></td>
      <td>
        <select name="cond0">
          <option value="1">未着手</option>
          <option value="2">着手中</option>
          <option value="3">完了</option>
        </select>
      </td>
      <td><input type="text" name="memo0"></td>
      <td><input type="date" name="startDate0"></td>
      <td><input type="date" name="endDate0"></td>
      <% } %>
    </table>
    <p><button type="submit" name="add">追加</button></p>
    <p><button type="submit" name="regist">登録</button></p>
    <p><button type="submit" name="delete">削除</button></p>
  </form>
  </body>
</html>
