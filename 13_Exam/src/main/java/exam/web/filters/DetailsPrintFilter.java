package exam.web.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailsPrintFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // check whether we have a httpServletRequest and a pattern
        if (request instanceof HttpServletRequest) {

            // resolve the query string from the httpServletRequest
            String queryString = ((HttpServletRequest) request).getQueryString();
            // check whether a query string exists and matches the given pattern
            if (queryString != null ) {
                ((HttpServletResponse) response).sendRedirect("/views/index.xhtml");
                return;
            }
            chain.doFilter(request, response);
        }
        chain.doFilter(request, response);
    }

}
