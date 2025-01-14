package controllers.users;

import dto.UserDTO;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/profile")
public class ProfileServlets extends HttpServlet {
    private UserServiceImpl userServiceImpl;
    @Override
    public void init() throws ServletException {
        userServiceImpl = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");
        UserDTO userDTO = userServiceImpl.getUserById(userId);

        if (userDTO != null) {
            req.setAttribute("user", userDTO);
            getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
        }


    }
}
