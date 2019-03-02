package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeDetailViewModel;
import metube.services.MeTubeSerivce;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/tube/details")
public class TubeDetailsServlet extends HttpServlet {
    private final MeTubeSerivce meTubeSerivce;
    private final ModelMapper modelMapper;

    @Inject
    public TubeDetailsServlet(MeTubeSerivce meTubeSerivce, ModelMapper modelMapper) {
        this.meTubeSerivce = meTubeSerivce;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getQueryString().split("id=")[1];
       TubeServiceModel tubeServiceModel = meTubeSerivce.findById(id).orElse(null);
        if (tubeServiceModel!=null){
        TubeDetailViewModel tubeDetailViewModel = modelMapper.map(tubeServiceModel, TubeDetailViewModel.class);
        req.setAttribute("tubeDetails", tubeDetailViewModel);}
        req.getRequestDispatcher("/jsp/tube-details.jsp").forward(req, resp);
    }
}
