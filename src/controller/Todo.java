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

        TodoModel todoModel = new TodoModel();
        try {
            todoModel.select();
        } catch (Exception e) {

        }
//        request.setAttribute("id", todoModel.id.get(0));
//        request.setAttribute("work", todoModel.work.get(0));
//        request.setAttribute("cond", Integer.parseInt(todoModel.cond.get(0)));
//        request.setAttribute("memo", todoModel.memo.get(0));
//        request.setAttribute("startDate", todoModel.startDate.get(0));
//        request.setAttribute("endDate", todoModel.endDate.get(0));
        request.setAttribute("model", todoModel);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        Map<String, String[]> form = request.getParameterMap();
        System.out.println(form.containsKey("add"));
        System.out.println(form.containsKey("regist"));
        TodoModel todoModel = new TodoModel();
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
                todoModel.id.add((String)request.getParameter("id" + i));
                todoModel.work.add((String)request.getParameter("work" + i));
                todoModel.cond.add((String)request.getParameter("cond" + i));
                todoModel.memo.add((String)request.getParameter("memo" + i));
                todoModel.startDate.add((String)request.getParameter("startDate" + i));
                todoModel.endDate.add((String)request.getParameter("endDate" + i));
            }
            try {
                todoModel.regist();
            } catch (Exception e) {

            }
        } else {
            try {
                todoModel.select();
            } catch (Exception e) {

            }

        }
//        todoModel.work.add((String)request.getParameter("work"));
//        todoModel.cond.add((String)request.getParameter("cond"));
//        todoModel.memo.add((String)request.getParameter("memo"));
//        todoModel.startDate.add((String)request.getParameter("startDate"));
//        todoModel.endDate.add((String)request.getParameter("endDate"));

//        try {
//            todoModel.insert();
//        } catch (Exception e) {
//
//        }


        request.setAttribute("model", todoModel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
