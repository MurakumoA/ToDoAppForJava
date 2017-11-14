package controller;
import model.TodoModel;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/todo")
public class Todo extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        int userId = (int)request.getSession(true).getAttribute("userId");

        TodoModel todoModel = new TodoModel();
        todoModel.userId = userId;
        try {
            todoModel.select();
        } catch (Exception e) {

        }
        request.setAttribute("model", todoModel);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        Map<String, String[]> form = request.getParameterMap();
        int userId = (int)request.getSession(true).getAttribute("userId");
        TodoModel todoModel = new TodoModel();
        todoModel.userId = userId;
        if (form.containsKey("add")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                todoModel.id.add((String)request.getParameter("id" + i));
                todoModel.work.add((String)request.getParameter("work" + i));
                todoModel.cond.add((String)request.getParameter("cond" + i));
                todoModel.memo.add((String)request.getParameter("memo" + i));
                todoModel.startDate.add((String)request.getParameter("startDate" + i));
                todoModel.endDate.add((String)request.getParameter("endDate" + i));
                System.out.println("todoModel:"+todoModel.id+todoModel.work+todoModel.cond+todoModel.memo);
            }
            todoModel.id.add("");
            todoModel.work.add("");
            todoModel.cond.add("0");
            todoModel.memo.add("");
            todoModel.startDate.add("");
            todoModel.endDate.add("");

        } else if (form.containsKey("regist")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                todoModel.id.add((String) request.getParameter("id" + i));
                todoModel.work.add((String) request.getParameter("work" + i));
                todoModel.cond.add((String) request.getParameter("cond" + i));
                todoModel.memo.add((String) request.getParameter("memo" + i));
                todoModel.startDate.add((String) request.getParameter("startDate" + i));
                todoModel.endDate.add((String) request.getParameter("endDate" + i));
            }
            try {
                todoModel.regist();
                todoModel.select();
            } catch (Exception e) {

            }
        } else if (form.containsKey("delete")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                if (request.getParameter("del" + i) == null) {
                    todoModel.id.add((String) request.getParameter("id" + i));
                    todoModel.work.add((String) request.getParameter("work" + i));
                    todoModel.cond.add((String) request.getParameter("cond" + i));
                    todoModel.memo.add((String) request.getParameter("memo" + i));
                    todoModel.startDate.add((String) request.getParameter("startDate" + i));
                    todoModel.endDate.add((String) request.getParameter("endDate" + i));
                } else {
                    String id = (String)request.getParameter("id" + i);
                    if (!"".equals(id)) {
                        try {
                            todoModel.delete(id);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        } else {
            try {
                todoModel.select();
            } catch (Exception e) {

            }

        }

        request.setAttribute("model", todoModel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
        dispatcher.forward(request,response);
    }

}
