package metube.web.servlets;

import metube.domain.models.binding.CreateTubeBindingModel;
import metube.domain.models.service.TubeServiceModel;
import metube.services.MeTubeSerivce;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tube/create")
public class TubeCreateServlet extends HttpServlet {
    private final MeTubeSerivce meTubeSerivce;
    private final ModelMapper modelMapper;

    @Inject
    public TubeCreateServlet(MeTubeSerivce meTubeSerivce, ModelMapper modelMapper) {
        this.meTubeSerivce = meTubeSerivce;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CreateTubeBindingModel tubeUploadBindingModel = (CreateTubeBindingModel) req
                .getAttribute("model");

        TubeServiceModel tubeServiceModel = this.modelMapper.map(tubeUploadBindingModel, TubeServiceModel.class);

        this.meTubeSerivce.create(tubeServiceModel);
        resp.sendRedirect("/");
    }
}
