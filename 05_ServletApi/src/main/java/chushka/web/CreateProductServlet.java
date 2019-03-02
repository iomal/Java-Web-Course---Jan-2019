package chushka.web;

import chushka.domain.entities.Product;
import chushka.domain.entities.Type;
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

@WebServlet("/products/create")
public class CreateProductServlet extends HttpServlet {
    private final String CREATE_PRODUCTS_HMTL = "views/create-product.html";
    private final HtmlReader htmlReader;
    private final ProductsService productsService;

    @Inject
    public CreateProductServlet(HtmlReader htmlReader, ProductsService productsService) {
        this.htmlReader = htmlReader;
        this.productsService = productsService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type").toLowerCase();
        String description = request.getParameter("description");
        ProductServiceModel product= new ProductServiceModel();
        product.setName(name);
        product.setDescription(description);
        product.setType(type);
        this.productsService.saveProduct(product);
        response.sendRedirect("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println(htmlReader.readFile(CREATE_PRODUCTS_HMTL));
    }
}
