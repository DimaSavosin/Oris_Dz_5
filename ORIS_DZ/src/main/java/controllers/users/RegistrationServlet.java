package controllers.users;

import dto.RegisterForm;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    UserService userService;
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        RegisterForm registerForm = new RegisterForm(name, email, password);

        boolean isRegistered = userService.registerUser(registerForm);
        if (!isRegistered) {
            req.setAttribute("errorMessage","Эта почта уже занята.");
            getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }

        int userId = userService.getUserIdByEmail(email);
        if(userId == -1) {
            req.setAttribute("errorMessage","Во время регистрации произошла ошибка. Пожалуйста, попробуйте снова.");
            getServletContext().getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }

        req.getSession().setAttribute("userId", userId);
        resp.sendRedirect(req.getContextPath() + "/mainPage");
    }
}
