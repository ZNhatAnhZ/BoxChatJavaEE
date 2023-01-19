package controller;

import dao.UserDao;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.*;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private UserDao UserDao;

    public void init() {
        UserDao = new UserDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirm_password = request.getParameter("confirm_password");
        StringBuilder result = new StringBuilder();

        if (username.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
            result.append("Please fill out all the information");
        } else {
            if (!password.equals(confirm_password)) {
                result.append("password confirmation is wrong!!");
            } else {
                User user = new User(username, password);
                int errorcode = 0;

                try {
                    errorcode = UserDao.registerUser(user);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (errorcode != 0) {
                    result.append("register successfully!!!");
                } else {
                    result.append("register failed!!!");
                }

            }
        }
        request.setAttribute("result", result.toString());
        getServletContext().getRequestDispatcher("/Registration.jsp").forward(request, response);
    }
}
