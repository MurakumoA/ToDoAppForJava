package controller;

import model.LoginModel;
import model.TodoModel;
import model.UserRegistModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login  extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request,response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //リクエストパラメータの文字コードを指定
        request.setCharacterEncoding("UTF-8");

        String mail = (String)request.getParameter("mail");
        String password = (String)request.getParameter("password");

        List<String> error = new ArrayList<String>();
        if ("".equals(mail)) {
            error.add("メールアドレスが入力されていません。");
        }
        if ("".equals(password)) {
            error.add("パスワードが入力されていません。");
        }

        if (error.size() > 0) {
            request.setAttribute("mail", mail);
            request.setAttribute("password", password);
            request.setAttribute("error", error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request,response);
        } else {

            LoginModel loginModel = new LoginModel();
            loginModel.mail = mail;
            loginModel.password = password;
            try {
                loginModel.confirm();
            } catch (Exception e) {

            }

            if (loginModel.loginCheck) {
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", loginModel.id);
                session.setAttribute("name", loginModel.name);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/todo");
//                dispatcher.forward(request, response);
                response.sendRedirect("/todo");
            } else {
                error.add("入力されたメールアドレスまたはパスワードに誤りがあります。");
                request.setAttribute("mail", mail);
                request.setAttribute("password", password);
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

}
