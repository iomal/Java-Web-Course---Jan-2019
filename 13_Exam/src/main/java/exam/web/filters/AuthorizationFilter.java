package exam.web.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/views/home.xhtml", "/views/schedule.xhtml", "/views/details.xhtml", "/views/print.xhtml"})
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String userId = (String) ((HttpServletRequest) servletRequest).getSession().getAttribute("userId");

        if (userId == null) {
            ((HttpServletResponse) servletResponse).sendRedirect("/views/login.xhtml");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
