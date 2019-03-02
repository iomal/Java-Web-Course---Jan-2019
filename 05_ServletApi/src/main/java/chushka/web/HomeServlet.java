package chushka.web;

import chushka.domain.entities.Product;
import chushka.domain.models.ProductServiceModel;
import chushka.service.ProductsService;
import chushka.utils.HtmlReader;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("")
public class HomeServlet extends HttpServlet {
    private final String INDEX_FILE_PATH = "views/index.html";
    private final HtmlReader htmlReader;
    private final ProductsService productsService;

    @Inject
    public HomeServlet(HtmlReader htmlReader, ProductsService productsService) {
        this.htmlReader = htmlReader;
        this.productsService = productsService;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductServiceModel> products = this.productsService.getAll();
        if (products.size() == 0) {
            resp.getWriter().println(htmlReader.readFile(INDEX_FILE_PATH).replace("{{products}}", ""));
        } else {
            String response = htmlReader.readFile(INDEX_FILE_PATH).replace("{{products}}", "<ul>" +
                            products.stream().map(p -> "<li> <a href=\"\\products\\details?id=" + p.getId() + "\"\\>"
                                    + p.getName() + "</li>").collect(Collectors.joining(System.lineSeparator())));

            resp.getWriter().println(response);
        }
    }
}
