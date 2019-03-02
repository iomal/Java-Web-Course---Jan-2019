package metube.web.filters;

import metube.domain.models.binding.CreateTubeBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tube/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getMethod().toLowerCase().equals("post")) {
            CreateTubeBindingModel createTubeBindingModel = new CreateTubeBindingModel();
            createTubeBindingModel.setLink(req.getParameter("link"));
            createTubeBindingModel.setName(req.getParameter("name"));
            createTubeBindingModel.setDescription( req.getParameter("descr"));
            createTubeBindingModel.setUploader(req.getParameter("uploader"));
            req.setAttribute("model", createTubeBindingModel);

        }
        chain.doFilter(req, resp);
    }

}