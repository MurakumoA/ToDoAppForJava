package controller;

import model.UserRegistModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userRegist")
public class UserRegist extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/userRegist.jsp");
        dispatcher.forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        String mail = (String)request.getParameter("mail");
        String name = (String)request.getParameter("name");
        String password = (String)request.getParameter("password");

        List<String> error = new ArrayList<String>();
        if ("".equals(mail)) {
            error.add("メールアドレスが入力されていません。");
        }
        if ("".equals(name)) {
            error.add("名前が入力されていません。");
        }
        if ("".equals(password)) {
            error.add("パスワードが入力されていません。");
        }

        if (error.size() > 0) {
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userRegist.jsp");
            dispatcher.forward(request,response);
        } else {

            UserRegistModel userRegistModel = new UserRegistModel();
            userRegistModel.setMail(mail);
            userRegistModel.setName(name);
            userRegistModel.setPassword(password);
            try {
                userRegistModel.insert();
                userRegistModel.getUserId();
            } catch (Exception e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("name", userRegistModel.getName());
            session.setAttribute("userId", userRegistModel.getId());
            response.sendRedirect("/todo");
        }
    }

}
