package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter(urlPatterns = {"/profile"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest; // приводим чтобы можно было использовать методы getSession
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Object userId = request.getSession().getAttribute("userId"); // Метод getAttribute возвращает общий тип Object

        if (userId == null){
            response.sendRedirect(request.getContextPath() + "/register");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
