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
        todoModel.setUserId(userId);
        try {
            todoModel.select();
        } catch (Exception e) {
            request.getSession(true).removeAttribute("userId");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
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
        todoModel.setUserId(userId);
        if (form.containsKey("add")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                todoModel.getId().add((String)request.getParameter("id" + i));
                todoModel.getWork().add((String)request.getParameter("work" + i));
                todoModel.getCond().add((String)request.getParameter("cond" + i));
                todoModel.getMemo().add((String)request.getParameter("memo" + i));
                todoModel.getStartDate().add((String)request.getParameter("startDate" + i));
                todoModel.getEndDate().add((String)request.getParameter("endDate" + i));
            }
            todoModel.getId().add("");
            todoModel.getWork().add("");
            todoModel.getCond().add("0");
            todoModel.getMemo().add("");
            todoModel.getStartDate().add("");
            todoModel.getEndDate().add("");

        } else if (form.containsKey("regist")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                todoModel.getId().add((String) request.getParameter("id" + i));
                todoModel.getWork().add((String) request.getParameter("work" + i));
                todoModel.getCond().add((String) request.getParameter("cond" + i));
                todoModel.getMemo().add((String) request.getParameter("memo" + i));
                todoModel.getStartDate().add((String) request.getParameter("startDate" + i));
                todoModel.getEndDate().add((String) request.getParameter("endDate" + i));
            }
            try {
                todoModel.regist();
                todoModel.select();
            } catch (Exception e) {
                request.getSession(true).removeAttribute("userId");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            }
        } else if (form.containsKey("delete")) {
            int count = Integer.parseInt(request.getParameter("count"));
            for (int i = 0; i < count; i++) {
                if (request.getParameter("del" + i) == null) {
                    todoModel.getId().add((String) request.getParameter("id" + i));
                    todoModel.getWork().add((String) request.getParameter("work" + i));
                    todoModel.getCond().add((String) request.getParameter("cond" + i));
                    todoModel.getMemo().add((String) request.getParameter("memo" + i));
                    todoModel.getStartDate().add((String) request.getParameter("startDate" + i));
                    todoModel.getEndDate().add((String) request.getParameter("endDate" + i));
                } else {
                    String id = (String)request.getParameter("id" + i);
                    if (!"".equals(id)) {
                        try {
                            todoModel.delete(id);
                        } catch (Exception e) {
                            request.getSession(true).removeAttribute("userId");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                }
            }
        } else {
            try {
                todoModel.select();
            } catch (Exception e) {
                request.getSession(true).removeAttribute("userId");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            }

        }

        request.setAttribute("model", todoModel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/todo.jsp");
        dispatcher.forward(request,response);
    }

}
