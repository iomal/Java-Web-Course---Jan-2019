package metube.web.servlets;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeViewModel;
import metube.services.MeTubeServiceImpl;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.IOException;


@WebServlet("/tube/all")
public class AllTubesServlet extends HttpServlet {
    private final MeTubeServiceImpl meTubeService;
    private final ModelMapper modelMapper;

    @Inject
    public AllTubesServlet(MeTubeServiceImpl meTubeService, ModelMapper modelMapper) {
        this.meTubeService = meTubeService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TubeServiceModel> tubeServiceModels = this.meTubeService.getAll();
        List<TubeViewModel> tubeViewModels = Arrays.asList(modelMapper.map(tubeServiceModels,
                TubeViewModel[].class));
        req.setAttribute("tubes",tubeViewModels);
        req.getRequestDispatcher("/jsp/all-tubes.jsp").forward(req, resp);
    }
}
