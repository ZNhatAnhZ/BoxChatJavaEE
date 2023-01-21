package controller;

import dao.MessageDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RoomChatServlet", value = "/RoomChatServlet")
public class RoomChatServlet extends HttpServlet {
    MessageDao messageDao;

    @Override
    public void init() throws ServletException {
        messageDao = new MessageDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/LoginServlet");
        } else {
            try {
                request.setAttribute("messages", messageDao.getAllMessages());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            getServletContext().getRequestDispatcher("/RoomChat.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
