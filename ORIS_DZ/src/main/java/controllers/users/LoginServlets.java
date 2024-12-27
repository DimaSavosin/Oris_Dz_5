package controllers.users;

import dto.UserDTO;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlets extends HttpServlet {
    private UserServiceImpl userServiceImpl;
    @Override
    public void init() throws ServletException {
        userServiceImpl = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDTO userDTO = userServiceImpl.loginUser(email,password);
        if(userDTO != null) {
            req.getSession().setAttribute("userId", userDTO.getId());
            req.getSession().setAttribute("role",userDTO.getRole());


            if ("admin".equalsIgnoreCase(userDTO.getRole())){
                resp.sendRedirect(req.getContextPath()+"/admin/mainPage");
            }
            else {
                resp.sendRedirect(req.getContextPath()+"/mainPage");
            }
        } else {
            req.setAttribute("errorMessage", "Неверный адрес электронной почты или пароль. Пробовать снова.");
            getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
