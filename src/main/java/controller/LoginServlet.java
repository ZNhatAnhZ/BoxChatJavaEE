package controller;

import dao.UserDao;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private dao.UserDao UserDao;

    public void init() {
        UserDao = new UserDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        StringBuilder destPage = new StringBuilder();

        if (username.isEmpty() || password.isEmpty()) {
            request.setAttribute("result", "Please fill out all the information");
            destPage.append("/index.jsp");
        } else {
            User user = new User(username, password);

            try {
                boolean isValidUser = UserDao.confirmUser(user);
                if (isValidUser) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    destPage.append("/RoomChatServlet");
                } else {
                    request.setAttribute("result", "Wrong username or password");
                    destPage.append("/index.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (destPage.toString().equals("/RoomChatServlet")) {
            response.sendRedirect(destPage.toString());
        } else {
            getServletContext().getRequestDispatcher(destPage.toString()).forward(request, response);
        }
    }
}
