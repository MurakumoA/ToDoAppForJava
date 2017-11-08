package controller;
import model.TodoModel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/todo")
public class Todo extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TodoModel todoModel = new TodoModel();
        try {
            todoModel.select();
        } catch (Exception e) {

        }
        request.setAttribute("id", todoModel.id.get(0));
        request.setAttribute("work", todoModel.work.get(0));
        request.setAttribute("cond", todoModel.cond.get(0));
        request.setAttribute("memo", todoModel.memo.get(0));
        request.setAttribute("startDate", todoModel.startDate.get(0));
        request.setAttribute("endDate", todoModel.endDate.get(0));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TodoModel todoModel = new TodoModel();
        todoModel.work.add((String)request.getParameter("work"));
        todoModel.cond.add((String)request.getParameter("cond"));
        todoModel.memo.add((String)request.getParameter("memo"));
        todoModel.startDate.add((String)request.getParameter("startDate"));
        todoModel.endDate.add((String)request.getParameter("endDate"));

        try {
            todoModel.insert();
        } catch (Exception e) {

        }
        request.setAttribute("id", todoModel.id);
        request.setAttribute("work", todoModel.work);
        request.setAttribute("cond", todoModel.cond);
        request.setAttribute("memo", todoModel.memo);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }
}
