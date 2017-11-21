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
            request.setAttribute("mail", mail);
            request.setAttribute("name", name);
            request.setAttribute("password", password);
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/userRegist.jsp");
            dispatcher.forward(request,response);
        } else {

            UserRegistModel userRegistModel = new UserRegistModel();
            userRegistModel.mail = mail;
            userRegistModel.name = name;
            userRegistModel.password = password;
            try {
                userRegistModel.insert();
                userRegistModel.getId();
            } catch (Exception e) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            }

            HttpSession session = request.getSession(true);
            session.setAttribute("name", userRegistModel.name);
            session.setAttribute("userId", userRegistModel.id);
            response.sendRedirect("/todo");
        }
    }

}
