package chushka.web;

import chushka.domain.entities.Product;
import chushka.domain.models.ProductServiceModel;
import chushka.service.ProductsService;
import chushka.utils.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {
    private final HtmlReader htmlReader;
    private final ProductsService productsService;

    @Inject
    public ProductDetailsServlet(HtmlReader htmlReader, ProductsService productsService) {
        this.htmlReader = htmlReader;
        this.productsService = productsService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id;
        if (request.getQueryString() != null) {
        id = request.getQueryString().split("=")[1];
        Optional<ProductServiceModel> product = this.productsService.getByID(id);
            if (product.isPresent()) {
        response.getWriter().println(htmlReader.readFile("views/details.html")
                .replace("{{name}}", product.get().getName()!=null?product.get().getName():"")
                .replace("{{description}}", product.get().getDescription()!=null?product.get().getDescription():"")
                .replace("{{type}}",product.get().getType()!=null?product.get().getType():""));
            } else {
                notSuchProduct(response);
            }

        } else notSuchProduct(response);
    }
    private void notSuchProduct(HttpServletResponse response) throws IOException {
        response.getWriter().println(htmlReader.readFile("views/details.html")
                .replace("{{name}}", "")
                .replace("{{description}}", "Not existing product!")
                .replace("{{type}}", ""));
    }
}
